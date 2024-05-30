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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category) {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var adapter: CategoryAdapter
    private val viewModel: CategoryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CategoryAdapter { category ->
            //onClick(category)
        }

        initObserver()

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCategoryBinding.bind(view)
        initViews()
        //initListeners()
        loadData(
        )
    }

    private fun loadData() {
        viewModel.getCategories()
    }

    private fun initObserver() {
        viewModel.category.observe(this){
            adapter.submitList(it.Data)
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

//    private fun onClick(category: CategoryItem) {
//        println("Clicked")
//        val action = CategoryFragmentDirections.actionCategoryFragmentToProductListFragment(0)
//        findNavController().navigate(action)
//    }



}