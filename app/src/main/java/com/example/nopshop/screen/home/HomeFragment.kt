package com.example.nopshop.screen.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nopshop.R
import com.example.nopshop.adapter.CategoryAdapter
import com.example.nopshop.adapter.CategoryListAdapter
import com.example.nopshop.adapter.FeatureProductsAdapter
import com.example.nopshop.databinding.FragmentHomeBinding
import com.example.nopshop.db.dbmodel.category.toData
import com.example.nopshop.db.dbmodel.featureProduct.toFeatureData
import com.example.nopshop.model.ProductItem
import com.example.nopshop.model.featureProducts.Data
import com.example.nopshop.screen.product.details.ProductDetailsViewModel
import com.example.nopshop.utils.Constants
import com.example.nopshop.utils.NoInternet
import dagger.hilt.android.AndroidEntryPoint
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import kotlin.properties.Delegates
import kotlin.reflect.jvm.internal.ReflectProperties.Val


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var featureProductsAdapter: FeatureProductsAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryListAdapter: CategoryListAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private val viewModel: HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()

        featureProductsAdapter = FeatureProductsAdapter(
            { product ->
                onProductClick(product)
            },
            { product ->
                onAddToCartClick(product)
            }
        )

        categoryAdapter = CategoryAdapter {}

        categoryListAdapter = CategoryListAdapter { list, name ->
            onCategoryClick(list, name)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        binding.adCarousel.registerLifecycle(viewLifecycleOwner)

        initViews()
        initListeners()
        loadData()
    }
    private fun initViews() {
        binding.categoryRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRv.adapter = categoryListAdapter

        binding.featureProductRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.featureProductRv.adapter = featureProductsAdapter

    }

    private fun onAddToCartClick(product: Data) {
        if (NoInternet.isOnline(requireContext())) {
            viewModel.addProductToCart(product.Id, 1)
        } else {
            Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onProductClick(it: Data) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(it.Id)
        findNavController().navigate(action)
    }

    private fun onCategoryClick(
        product: com.example.nopshop.model.category.Data, categoryName: String
    ) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductListFragment(
            product.Products.toTypedArray(), categoryName
        )
        findNavController().navigate(action)
    }

    private fun initObserver() {
        viewModel.sliderImageResponse.observe(this) { slider ->
            for (image in slider) {
                binding.adCarousel.addData(
                    CarouselItem(
                        imageUrl = image.ImageUrl
                    )
                )
            }
        }
        viewModel.featureProductsResponse.observe(this) {
            featureProductsAdapter.submitList(it)
        }
        viewModel.categoryWiseProductsResponse.observe(this) {
            categoryListAdapter.submitList(it)
        }
        viewModel.productAddedToCart.observe(this) {
            Toast.makeText(requireContext(), it.Message, Toast.LENGTH_SHORT).show()
            if (it.Message.isNotEmpty()) {
                viewModel.getCartItemCount()
            }
        }
        viewModel.itemCount.observe(this) {
            binding.cartBadge.text = it.Data.Cart.Items.size.toString()
        }
        viewModel.showMessage.observe(this){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadData() {
        viewModel.getImageSlider()
        viewModel.getFeatureProducts()
        viewModel.getCategoryWiseProducts()
        viewModel.getCartItemCount()
    }

    private fun initListeners() {
        binding.cartBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToCartFragment()
            findNavController().navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        sharedPreferences =
            requireActivity().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        Constants.TOKEN = sharedPreferences.getString("auth_token", null)
        if(!NoInternet.isOnline(requireContext().applicationContext)){
            Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }
}