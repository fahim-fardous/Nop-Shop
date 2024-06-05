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
import com.example.nopshop.adapter.BestSellingAdapter
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
import kotlin.reflect.jvm.internal.ReflectProperties.Val


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var bestSellingAdapter: BestSellingAdapter
    private lateinit var featureProductsAdapter: FeatureProductsAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryListAdapter: CategoryListAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private val viewModel: HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences =  requireActivity().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        Constants.TOKEN = sharedPreferences.getString("Token", null)
        initObserver()

        bestSellingAdapter = BestSellingAdapter {}

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

        val bestSellingList = mutableListOf<ProductItem>()

        bestSellingList.add(
            ProductItem(
                0, R.drawable.orange, "California Orange 8 Pcs", 3.0f, 15.00

            )
        )

        bestSellingList.add(
            ProductItem(
                0, R.drawable.steak, "California Orange 8 Pcs", 3f, 15.00

            )
        )

        bestSellingList.add(
            ProductItem(
                0, R.drawable.furniture, "California Orange 8 Pcs", 3f, 15.00

            )
        )

        bestSellingList.add(
            ProductItem(
                0, R.drawable.furniture_3, "California Orange 8 Pcs", 3f, 15.00

            )
        )

        bestSellingList.add(
            ProductItem(
                0, R.drawable.furniture_2, "California Orange 8 Pcs", 3f, 15.00

            )
        )

        binding.bestSellingRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.bestSellingRv.adapter = bestSellingAdapter
        bestSellingAdapter.submitList(bestSellingList)


        binding.categoryRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRv.adapter = categoryListAdapter


        binding.featureProductRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.featureProductRv.adapter = featureProductsAdapter

    }

    private fun onAddToCartClick(product: Data) {
        viewModel.addProductToCart(product.Id, 1)
        //Value.incrementValue()
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
        if (NoInternet.isOnline(requireContext().applicationContext)) {
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
                featureProductsAdapter.submitList(it.Data)
            }
            viewModel.categoryWiseProductsResponse.observe(this) { it ->
                categoryListAdapter.submitList(it.Data)
            }
            viewModel.productAddedToCart.observe(this) {
                Toast.makeText(requireContext(), it.Message, Toast.LENGTH_SHORT).show()
                if(it.Message.isNotEmpty()){
                    viewModel.getCartItemCount()
                }
            }
            viewModel.itemCount.observe(this){
                binding.cartBadge.text = it.Data.Cart.Items.size.toString()
            }
        } else {
            viewModel.sliderImageResponseFromDb.observe(this) { slider ->
                for (image in slider) {
                    binding.adCarousel.addData(
                        CarouselItem(
                            imageUrl = image.ImageUrl
                        )
                    )
                }
            }
            viewModel.featureProductsResponseFromDb.observe(this) {
                val list = mutableListOf<Data>()
                for (item in it) {
                    list.add(
                        item.toFeatureData()
                    )
                }

                featureProductsAdapter.submitList(list)
            }
            viewModel.categoryWiseProductsResponseFromDb.observe(this) {
                val list = mutableListOf<com.example.nopshop.model.category.Data>()
                for (item in it) {
                    list.add(
                        item.toData()
                    )
                }
                categoryListAdapter.submitList(list)
            }
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
}