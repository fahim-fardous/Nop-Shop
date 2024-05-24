package com.example.nopshop.screen.product.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nopshop.R
import com.example.nopshop.adapter.ProductAdapter
import com.example.nopshop.databinding.FragmentProductListBinding
import com.example.nopshop.databinding.ItemCategoryListBinding
import com.example.nopshop.model.ProductItem
import com.example.nopshop.model.products.ProductsItem


class ProductListFragment : Fragment(R.layout.fragment_product_list) {
    private lateinit var binding: FragmentProductListBinding

    private lateinit var adapter: ProductAdapter
    private lateinit var args: ProductListFragmentArgs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ProductAdapter { product ->
            onClick(product)
        }
        initObserver()
    }

    private fun onClick(it: ProductsItem) {
//        val action =
//            ProductListFragmentDirections.actionProductListFragmentToProductDetailsFragment()
//        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProductListBinding.bind(view)

        //initListeners()
        initViews()
        //loadData()

    }

    private fun initObserver() {
        view
    }

    private fun loadData() {
        TODO("Not yet implemented")
    }

    private fun initViews() {

        binding.productRv.layoutManager =
            GridLayoutManager(requireContext(), 2)
        binding.productRv.adapter = adapter


    }

    private fun initListeners() {
        binding.topBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}