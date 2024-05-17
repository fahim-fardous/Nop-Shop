package com.example.nopshop.screen.product.details

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nopshop.R
import com.example.nopshop.databinding.FragmentProductDetailsBinding

class ProductDetailsFragment : Fragment(R.layout.fragment_product_details) {
    private lateinit var binding: FragmentProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //initObserver()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProductDetailsBinding.bind(view)
        initViews()
        //initListeners()
        //loadData()
    }

    private fun loadData() {
        TODO("Not yet implemented")
    }

    private fun initListeners() {
        TODO("Not yet implemented")
    }

    private fun initViews() {
        binding.discountPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
    }

    private fun initObserver(){}


}