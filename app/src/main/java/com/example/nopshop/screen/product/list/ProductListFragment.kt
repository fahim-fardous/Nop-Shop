package com.example.nopshop.screen.product.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nopshop.R
import com.example.nopshop.adapter.ProductAdapter
import com.example.nopshop.databinding.FragmentProductListBinding
import com.example.nopshop.databinding.ItemCategoryListBinding
import com.example.nopshop.model.ProductItem
import com.example.nopshop.model.category.Product
import com.example.nopshop.model.products.ProductsItem
import com.example.nopshop.screen.product.details.ProductDetailsViewModel
import com.example.nopshop.utils.NoInternet
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductListFragment : Fragment(R.layout.fragment_product_list) {
    private lateinit var binding: FragmentProductListBinding
    private val viewModel: ProductListViewModel by viewModels()
    private lateinit var adapter: ProductAdapter
    private val args: ProductListFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ProductAdapter(
            { product ->
                onClick(product)
            },
            { product ->
                onAddToCartClick(product)
            }
        )
        initObserver()
    }

    private fun onAddToCartClick(product: Product) {
        viewModel.addProductToCart(product.Id, 1)
    }

    private fun onClick(it: Product) {
        val action =
            ProductListFragmentDirections.actionProductListFragmentToProductDetailsFragment(it.Id)
        findNavController().navigate(action)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProductListBinding.bind(view)

        initListeners()
        initViews()
        loadData()

    }

    private fun initObserver() {
        viewModel.productAddedToCart.observe(this) {
            Toast.makeText(requireContext(), it.Message, Toast.LENGTH_SHORT).show()
        }
        viewModel.itemCount.observe(this) {
            binding.cartBadge.text = it.toString()
        }
        viewModel.showMessage.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadData() {
        viewModel.getCartItemCount()
    }

    private fun initViews() {

        binding.productRv.layoutManager =
            GridLayoutManager(requireContext(), 2)
        binding.productRv.adapter = adapter
        adapter.submitList(args.productList.toMutableList())
        binding.categoryTitleTv.text = args.categoryName
        binding.categoryTv.text = args.categoryName

    }

    private fun initListeners() {
        binding.topBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.cartBtn.setOnClickListener {
            val action = ProductListFragmentDirections.actionProductListFragmentToCartFragment()
            findNavController().navigate(action)
        }
    }
}