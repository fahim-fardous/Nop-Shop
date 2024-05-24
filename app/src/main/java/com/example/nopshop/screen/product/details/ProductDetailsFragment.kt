package com.example.nopshop.screen.product.details

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.nopshop.R
import com.example.nopshop.databinding.FragmentProductDetailsBinding

class ProductDetailsFragment : Fragment(R.layout.fragment_product_details) {
    private lateinit var binding: FragmentProductDetailsBinding
    private val args:ProductDetailsFragmentArgs by navArgs()
    private val viewModel:ProductDetailsViewModel by viewModels()

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

    private fun initObserver(){
        viewModel.productResponse.observe(this){
            binding.productImg.load(it.Data.PictureModels[0].ImageUrl)
            binding.productTitleTv.text = it.Data.Name
            binding.productSubtitleTv.text = it.Data.ShortDescription
            if(it.Data.ProductPrice.PriceWithDiscount!=null){
                binding.discountPrice.text = it.Data.ProductPrice.PriceWithDiscount.toString()
                binding.discountPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
            else{
                binding.discountPrice.visibility = View.INVISIBLE
            }
            binding.originalPrice.text = it.Data.ProductPrice.Price
            binding.stockTv.text = it.Data.StockAvailability
            binding.descriptionTv.text = it.Data.FullDescription
        }
    }


}