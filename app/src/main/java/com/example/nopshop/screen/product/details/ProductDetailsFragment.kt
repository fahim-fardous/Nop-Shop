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
    private val viewModel: ProductDetailsViewModel by viewModels() {
        ProductDetailsViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProductDetailsBinding.bind(view)
        initViews()
        initListeners()
        loadData()
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

    }


    private fun initObserver() {
        if (NoInternet.isOnline(requireContext())) {
            viewModel.productResponse.observe(this) {
                binding.productImg.load(it.Data.PictureModels[0].ImageUrl)
                binding.productTitleTv.text = it.Data.Name
                binding.stockTv.text = it.Data.StockAvailability
                if (isHtmlString(it.Data.ShortDescription)) {
                    binding.productSubtitleTv.text =
                        Html.fromHtml(it.Data.ShortDescription, Html.FROM_HTML_MODE_COMPACT)
                            .toString()
                } else {
                    binding.productSubtitleTv.text = it.Data.ShortDescription
                }
                binding.descriptionTv.text =
                    Html.fromHtml(it.Data.FullDescription, Html.FROM_HTML_MODE_COMPACT).toString()
                binding.discountPrice.text = it.Data.ProductPrice.BasePricePAngVValue.toString()
                binding.originalPrice.text = it.Data.ProductPrice.Price
                binding.originalPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
            viewModel.productAddedToCart.observe(this) {
                Toast.makeText(requireContext(), it.Message, Toast.LENGTH_SHORT).show()
            }
        } else {
            viewModel.productResponseFromDb.observe(this) {
                binding.productImg.load(it.productImage)
                binding.productTitleTv.text = it.productName
                binding.stockTv.text = it.stock
                if (isHtmlString(it.productShortDescription)) {
                    binding.productSubtitleTv.text =
                        Html.fromHtml(it.productShortDescription, Html.FROM_HTML_MODE_COMPACT)
                            .toString()
                } else {
                    binding.productSubtitleTv.text = it.productShortDescription
                }
                binding.descriptionTv.text =
                    Html.fromHtml(it.productLongDescription, Html.FROM_HTML_MODE_COMPACT).toString()
                binding.discountPrice.text = it.newPrice
                binding.originalPrice.text = it.oldPrice
                binding.originalPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
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