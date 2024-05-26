package com.example.nopshop.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nopshop.R
import com.example.nopshop.adapter.BestSellingAdapter
import com.example.nopshop.adapter.CategoryAdapter
import com.example.nopshop.adapter.CategoryListAdapter
import com.example.nopshop.adapter.FeatureProductsAdapter
import com.example.nopshop.adapter.FurnitureCollectionAdapter
import com.example.nopshop.adapter.SalmonFishAdapter
import com.example.nopshop.adapter.WomenHeelAdapter
import com.example.nopshop.databinding.FragmentHomeBinding
import com.example.nopshop.db.dbmodel.toData
import com.example.nopshop.model.ProductItem
import com.example.nopshop.model.common.BaseCategoryItem
import com.example.nopshop.model.featureProducts.Data
import com.example.nopshop.utils.NoInternet
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var bestSellingAdapter: BestSellingAdapter
    private lateinit var featureProductsAdapter: FeatureProductsAdapter
    private lateinit var womenHeelAdapter: WomenHeelAdapter
    private lateinit var salmonFishAdapter: SalmonFishAdapter
    private lateinit var furnitureCollectionAdapter: FurnitureCollectionAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryListAdapter: CategoryListAdapter
    private val viewModel: HomeViewModel by viewModels() {
        HomeViewModelFactory(requireContext().applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()

        bestSellingAdapter = BestSellingAdapter {}

        featureProductsAdapter = FeatureProductsAdapter {
            onProductClick(it)
        }

        womenHeelAdapter = WomenHeelAdapter {}

        salmonFishAdapter = SalmonFishAdapter {}

        furnitureCollectionAdapter = FurnitureCollectionAdapter { }

        categoryAdapter = CategoryAdapter {}

        categoryListAdapter = CategoryListAdapter { list, name ->
            onCategoryClick(list, name)

        }
    }

    private fun onProductClick(it: Data) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(it.Id)
        findNavController().navigate(action)
    }

    private fun onCategoryClick(
        product: com.example.nopshop.model.category.Data,
        categoryName: String
    ) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductListFragment(
            product.Products.toTypedArray(),
            categoryName
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
        }
        viewModel.featureProductsResponse.observe(this) {
            featureProductsAdapter.submitList(it.Data)
        }
        if (NoInternet.isOnline(requireContext().applicationContext)) {
            viewModel.categoryWiseProductsResponse.observe(this) { it ->
                categoryListAdapter.submitList(it.Data)
            }
        } else {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        binding.adCarousel.registerLifecycle(viewLifecycleOwner)

        initViews()
        //initListeners()
        loadData()
    }

    private fun loadData() {
        viewModel.getImageSlider(requireContext())
        viewModel.getFeatureProducts()
        viewModel.getCategoryWiseProducts(requireContext())
    }

    private fun initListeners() {

    }

    private fun initViews() {

        val bestSellingList = mutableListOf<ProductItem>()

        bestSellingList.add(
            ProductItem(
                0,
                R.drawable.orange,
                "California Orange 8 Pcs",
                3.0f,
                15.00

            )
        )

        bestSellingList.add(
            ProductItem(
                0,
                R.drawable.steak,
                "California Orange 8 Pcs",
                3f,
                15.00

            )
        )

        bestSellingList.add(
            ProductItem(
                0,
                R.drawable.furniture,
                "California Orange 8 Pcs",
                3f,
                15.00

            )
        )

        bestSellingList.add(
            ProductItem(
                0,
                R.drawable.furniture_3,
                "California Orange 8 Pcs",
                3f,
                15.00

            )
        )

        bestSellingList.add(
            ProductItem(
                0,
                R.drawable.furniture_2,
                "California Orange 8 Pcs",
                3f,
                15.00

            )
        )

        val womenHeelList = mutableListOf<ProductItem>()

        womenHeelList.add(
            ProductItem(
                id = 0,
                productImage = R.drawable.womenheel_1,
                productName = "Women heel type 1",
                rating = 4.0f,
                price = 15.00
            )
        )

        womenHeelList.add(
            ProductItem(
                id = 0,
                productImage = R.drawable.womenheel_2,
                productName = "Women heel type 2",
                rating = 4.5f,
                price = 15.00
            )
        )

        womenHeelList.add(
            ProductItem(
                id = 0,
                productImage = R.drawable.womenheel_1,
                productName = "Women heel type 3",
                rating = 4.0f,
                price = 15.00
            )
        )
        womenHeelList.add(
            ProductItem(
                id = 0,
                productImage = R.drawable.womenheel_2,
                productName = "Women heel type 4",
                rating = 4.0f,
                price = 15.00
            )
        )

        val salmonFishList = mutableListOf<ProductItem>()

        salmonFishList.add(
            ProductItem(
                id = 0,
                productImage = R.drawable.salmon_fish,
                productName = "Salmon fish fry",
                rating = 4.0f,
                price = 15.00
            )
        )

        salmonFishList.add(
            ProductItem(
                id = 0,
                productImage = R.drawable.salmon_fish_steak,
                productName = "Salmon fish steak",
                rating = 4.5f,
                price = 15.00
            )
        )

        salmonFishList.add(
            ProductItem(
                id = 0,
                productImage = R.drawable.salmon_fish,
                productName = "Salmon fish fry",
                rating = 4.0f,
                price = 15.00
            )
        )
        salmonFishList.add(
            ProductItem(
                id = 0,
                productImage = R.drawable.salmon_fish_steak,
                productName = "Salmon fish steak",
                rating = 4.0f,
                price = 15.00
            )
        )

        val furnitureList = mutableListOf<ProductItem>()

        furnitureList.add(
            ProductItem(
                id = 0,
                productImage = R.drawable.furniture,
                productName = "Furniture sofa 1",
                rating = 4.0f,
                price = 15.00
            )
        )

        furnitureList.add(
            ProductItem(
                id = 0,
                productImage = R.drawable.furniture_2,
                productName = "Furniture sofa 2",
                rating = 4.5f,
                price = 15.00
            )
        )

        furnitureList.add(
            ProductItem(
                id = 0,
                productImage = R.drawable.furniture_collection_1,
                productName = "Furniture sofa 3",
                rating = 4.0f,
                price = 15.00
            )
        )
        furnitureList.add(
            ProductItem(
                id = 0,
                productImage = R.drawable.furniture_collection_2,
                productName = "Furniture chair",
                rating = 4.0f,
                price = 15.00
            )
        )


        //binding.adCarousel.setData(list)

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

        binding.womenHeelRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.womenHeelRv.adapter = womenHeelAdapter
        womenHeelAdapter.submitList(womenHeelList)

        binding.salmonFishRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.salmonFishRv.adapter = salmonFishAdapter
        salmonFishAdapter.submitList(salmonFishList)

        binding.furnitureCollectionRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.furnitureCollectionRv.adapter = furnitureCollectionAdapter
        furnitureCollectionAdapter.submitList(furnitureList)

        binding.cartBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToCartFragment()
            findNavController().navigate(action)
        }


    }
}