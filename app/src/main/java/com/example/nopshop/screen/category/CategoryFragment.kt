package com.example.nopshop.screen.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nopshop.R
import com.example.nopshop.adapter.CategoryAdapter
import com.example.nopshop.adapter.CategoryListAdapter
import com.example.nopshop.databinding.FragmentCategoryBinding
import com.example.nopshop.model.CategoryItem

class CategoryFragment : Fragment(R.layout.fragment_category) {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var adapter: CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = CategoryAdapter { category ->
            onClick(category)
        }
        //initObserver()

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCategoryBinding.bind(view)
        initViews()
        //initListeners()
    }

    private fun initObserver() {}

    private fun initViews() {
        val categoryList = mutableListOf<CategoryItem>()

        categoryList.add(
            CategoryItem(
                id = 0,
                categoryName = "Foods",
                categoryImage = R.drawable.strawberry
            )
        )

        categoryList.add(
            CategoryItem(
                id = 1,
                categoryName = "Watch",
                categoryImage = R.drawable.watch
            )
        )

        categoryList.add(
            CategoryItem(
                id = 2,
                categoryName = "Phones",
                categoryImage = R.drawable.mobile
            )
        )

        categoryList.add(
            CategoryItem(
                id = 3,
                categoryName = "Furniture",
                categoryImage = R.drawable.furniture_category
            )
        )

        categoryList.add(
            CategoryItem(
                id = 0,
                categoryName = "Foods",
                categoryImage = R.drawable.strawberry
            )
        )

        categoryList.add(
            CategoryItem(
                id = 1,
                categoryName = "Watch",
                categoryImage = R.drawable.watch
            )
        )

        categoryList.add(
            CategoryItem(
                id = 2,
                categoryName = "Phones",
                categoryImage = R.drawable.mobile
            )
        )

        categoryList.add(
            CategoryItem(
                id = 3,
                categoryName = "Furniture",
                categoryImage = R.drawable.furniture_category
            )
        )

        categoryList.add(
            CategoryItem(
                id = 0,
                categoryName = "Foods",
                categoryImage = R.drawable.strawberry
            )
        )

        categoryList.add(
            CategoryItem(
                id = 1,
                categoryName = "Watch",
                categoryImage = R.drawable.watch
            )
        )

        categoryList.add(
            CategoryItem(
                id = 2,
                categoryName = "Phones",
                categoryImage = R.drawable.mobile
            )
        )

        categoryList.add(
            CategoryItem(
                id = 3,
                categoryName = "Furniture",
                categoryImage = R.drawable.furniture_category
            )
        )
        binding.categoryRv.layoutManager =
            GridLayoutManager(requireContext(), 3)
        binding.categoryRv.adapter = adapter


    }

    private fun initListeners() {}

    private fun onClick(category: CategoryItem) {
        println("Clicked")
        val action = CategoryFragmentDirections.actionCategoryFragmentToProductListFragment(0)
        findNavController().navigate(action)
    }



}