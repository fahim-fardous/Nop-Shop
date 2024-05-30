package com.example.nopshop.screen.product.details

import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.nopshop.R
import com.example.nopshop.databinding.FragmentProductDetailsBinding
import com.example.nopshop.utils.NoInternet
import org.jsoup.Jsoup
import org.jsoup.parser.Parser

class ProductDetailsFragment : Fragment(R.layout.fragment_product_details) {
    private lateinit var binding: FragmentProductDetailsBinding
    private val args: ProductDetailsFragmentArgs by navArgs()
    private val viewModel: ProductDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProductDetailsBinding.bind(view)
        initObserver()
        initViews()
        initListeners()
        initShimmerEffect()
        loadData()
    }

    private fun initShimmerEffect() {
        binding.scrollView.visibility = View.GONE
        binding.productDetailsShimmer.visibility =View.VISIBLE
        binding.productDetailsShimmer.startShimmer()
    }

    private fun loadData() {
        viewModel.getProducts(requireContext(), args.productId)
    }

    private fun initListeners() {
        binding.topBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.addBtn.setOnClickListener {
            binding.quantityTv.text = binding.quantityTv.text.toString().toInt().plus(1).toString()
        }

        binding.removeBtn.setOnClickListener {
            if (binding.quantityTv.text.toString().toInt() > 1) {
                binding.quantityTv.text =
                    binding.quantityTv.text.toString().toInt().minus(1).toString()
            } else {
                Toast.makeText(requireContext(), "Minimum quantity is 1", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnAddToCart.setOnClickListener {
            viewModel.addProductToCart(args.productId, binding.quantityTv.text.toString().toInt())
        }

        binding.cartBtn.setOnClickListener {
            val action =
                ProductDetailsFragmentDirections.actionProductDetailsFragmentToCartFragment()
            findNavController().navigate(action)
        }
    }

    private fun initViews() {
        binding.productDetailsShimmer.stopShimmer()
        binding.productDetailsShimmer.visibility = View.GONE
        binding.scrollView.visibility = View.VISIBLE
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun initObserver() {
        if (NoInternet.isOnline(requireContext())) {
            viewModel.productResponse.observe(viewLifecycleOwner) {
                println("Ami ekhane eshechi -->" + it.Data)
                binding.productImg.load(it.Data.PictureModels[0].ImageUrl)
                binding.productTitleTv.text = it.Data.Name
                binding.stockTv.text = it.Data.StockAvailability ?: "Out of Stock"
                if (isHtmlString(it.Data.ShortDescription)) {
                    binding.productSubtitleTv.text =
                        Html.fromHtml(it.Data.ShortDescription, Html.FROM_HTML_MODE_LEGACY) ?: ""
                } else {
                    binding.productSubtitleTv.text = it.Data.ShortDescription ?: ""
                }
                if (isHtmlString(it.Data.FullDescription)) {
                    binding.descriptionTv.text =
                        Html.fromHtml(it.Data.FullDescription, Html.FROM_HTML_MODE_LEGACY) ?: ""
                } else {
                    binding.descriptionTv.text = it.Data.FullDescription ?: ""
                }
                binding.discountPrice.text = "$%.2f".format(it.Data.ProductPrice.BasePricePAngVValue)
                binding.originalPrice.text = "$%.2f".format(it.Data.ProductPrice.PriceValue)
                binding.originalPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                initViews()
            }
            viewModel.productAddedToCart.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it.Message, Toast.LENGTH_SHORT).show()
            }
        } else {
            viewModel.productResponseFromDb.observe(viewLifecycleOwner) {
                binding.productImg.load(it.productImage)
                binding.productTitleTv.text = it.productName
                binding.stockTv.text = it.stock
                if (it.productShortDescription?.let { it1 -> isHtmlString(it1) } == true) {
                    binding.productSubtitleTv.text =
                        Html.fromHtml(it.productShortDescription, Html.FROM_HTML_MODE_COMPACT)
                            .toString()
                } else {
                    binding.productSubtitleTv.text = it.productShortDescription
                }
                if (it.productLongDescription?.let { it1 -> isHtmlString(it1) } == true) {
                    binding.descriptionTv.text =
                        Html.fromHtml(it.productLongDescription, Html.FROM_HTML_MODE_LEGACY) ?: ""
                } else {
                    binding.descriptionTv.text = it.productLongDescription ?: ""
                }
                binding.discountPrice.text = it.newPrice
                binding.originalPrice.text = it.oldPrice
                binding.originalPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                initViews()
            }
        }
    }

    private fun isHtmlString(description: String): Boolean {
        return try {
            val doc = Jsoup.parse(description, "", Parser.xmlParser())
            doc.body().children().isNotEmpty()
        } catch (e: Exception) {
            false
        }
    }


}