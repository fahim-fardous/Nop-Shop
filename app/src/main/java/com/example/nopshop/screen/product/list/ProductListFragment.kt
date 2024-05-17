package com.example.nopshop.screen.product.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nopshop.R
import com.example.nopshop.adapter.ProductAdapter
import com.example.nopshop.databinding.FragmentProductListBinding
import com.example.nopshop.databinding.ItemCategoryListBinding
import com.example.nopshop.model.ProductItem


class ProductListFragment : Fragment(R.layout.fragment_product_list) {
    private lateinit var binding: FragmentProductListBinding
    private lateinit var adapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ProductAdapter {}
        //initObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProductListBinding.bind(view)

        //initListeners()
        initViews()
        //loadData()

    }

    private fun initObserver(){}

    private fun loadData() {
        TODO("Not yet implemented")
    }

    private fun initViews() {
        val productList = mutableListOf<ProductItem>()

        productList.add(
            ProductItem(
                id = 0,
                productImage = R.drawable.fashion_model,
                productName = "Women outfit for show",
                rating = 4.0f,
                price = 15.00
            )
        )

        productList.add(
            ProductItem(
                id = 0,
                productImage = R.drawable.essentials_hoodie,
                productName = "Fear of God",
                rating = 4.5f,
                price = 15.00
            )
        )

        productList.add(
            ProductItem(
                id = 0,
                productImage = R.drawable.max_tshirt,
                productName = "Max 90 t-shirt for men",
                rating = 4.0f,
                price = 15.00
            )
        )
        productList.add(
            ProductItem(
                id = 0,
                productImage = R.drawable.nike_sportwear,
                productName = "Nike Sportswear",
                rating = 4.0f,
                price = 15.00
            )
        )

        binding.productRv.layoutManager =
            GridLayoutManager(requireContext(), 2)
        binding.productRv.adapter = adapter
        adapter.submitList(productList)

        binding.categoryTitleTv.text = "Random"

    }

    private fun initListeners() {
        TODO("Not yet implemented")
    }
}