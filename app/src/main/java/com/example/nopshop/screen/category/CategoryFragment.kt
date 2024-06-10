package com.example.nopshop.screen.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nopshop.MainActivity
import com.example.nopshop.R
import com.example.nopshop.adapter.CategoryAdapter
import com.example.nopshop.adapter.CategoryListAdapter
import com.example.nopshop.databinding.FragmentCategoryBinding
import com.example.nopshop.model.CategoryItem
import com.example.nopshop.model.category.Data
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category) {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var adapter: CategoryAdapter
    private val viewModel: CategoryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CategoryAdapter { category ->
            onClick(category)
        }
        initObserver()

    }

    private fun onClick(category: Data) {
        val action = CategoryFragmentDirections.actionCategoryFragmentToProductListFragment(
            category.Products.toTypedArray(),
            category.Name
        )
        findNavController().navigate(action)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCategoryBinding.bind(view)
        initViews()
        initListeners()
        loadData(
        )
    }

    private fun loadData() {
        viewModel.getCategories()
        viewModel.getCartItemCount()
    }

    private fun initObserver() {
        viewModel.category.observe(this) {
            adapter.submitList(it.Data)
        }
        viewModel.itemCount.observe(this) {
            binding.cartBadge.text = it.Data.Cart.Items.size.toString()
        }
    }

    private fun initViews() {
        binding.categoryRv.layoutManager =
            GridLayoutManager(requireContext(), 3)
        binding.categoryRv.adapter = adapter


    }

    private fun initListeners() {
        binding.cartBtn.setOnClickListener {
            val action = CategoryFragmentDirections.actionCategoryFragmentToCartFragment()
            findNavController().navigate(action)
        }
    }
}