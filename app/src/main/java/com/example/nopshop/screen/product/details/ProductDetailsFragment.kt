package com.example.nopshop.screen.product.details

import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.nopshop.R
import com.example.nopshop.databinding.FragmentProductDetailsBinding

class ProductDetailsFragment : Fragment(R.layout.fragment_product_details) {
    private lateinit var binding: FragmentProductDetailsBinding
    private val args: ProductDetailsFragmentArgs by navArgs()
    private val viewModel: ProductDetailsViewModel by viewModels()


    @RequiresApi(Build.VERSION_CODES.N)
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
        viewModel.getProducts(args.productId)
    }

    private fun initListeners() {
        binding.topBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initViews() {
        binding.discountPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initObserver() {
        viewModel.productResponse.observe(this) {
            binding.stockTv.text = it.Data.StockAvailability
            binding.productImg.load(it.Data.PictureModels[0].ImageUrl)
            binding.productTitleTv.text = it.Data.Name
            binding.productSubtitleTv.text =
                Html.fromHtml(it.Data.ShortDescription, Html.FROM_HTML_MODE_COMPACT).toString()
            if (it.Data.ProductPrice.PriceWithDiscount != "") {
                binding.discountPrice.text = it.Data.ProductPrice.PriceWithDiscount.toString()
                binding.discountPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                binding.discountPrice.visibility = View.INVISIBLE
            }
            binding.originalPrice.text = it.Data.ProductPrice.Price
            binding.descriptionTv.text =
                Html.fromHtml(it.Data.FullDescription, Html.FROM_HTML_MODE_COMPACT).toString()
        }
    }


}