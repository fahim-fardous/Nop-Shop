package com.example.nopshop.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nopshop.R
import com.example.nopshop.adapter.BestSellingAdapter
import com.example.nopshop.adapter.CategoryAdapter
import com.example.nopshop.adapter.FeatureProductsAdapter
import com.example.nopshop.adapter.FurnitureCollectionAdapter
import com.example.nopshop.adapter.SalmonFishAdapter
import com.example.nopshop.adapter.WomenHeelAdapter
import com.example.nopshop.databinding.FragmentHomeBinding
import com.example.nopshop.model.ProductItem
import com.example.nopshop.model.CategoryItem
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var bestSellingAdapter: BestSellingAdapter
    private lateinit var featureProductsAdapter: FeatureProductsAdapter
    private lateinit var womenHeelAdapter: WomenHeelAdapter
    private lateinit var salmonFishAdapter: SalmonFishAdapter
    private lateinit var furnitureCollectionAdapter: FurnitureCollectionAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //initObserver()

        bestSellingAdapter = BestSellingAdapter {}

        featureProductsAdapter = FeatureProductsAdapter {}

        womenHeelAdapter = WomenHeelAdapter {}

        salmonFishAdapter = SalmonFishAdapter {}

        furnitureCollectionAdapter = FurnitureCollectionAdapter {  }

        categoryAdapter = CategoryAdapter {}


    }

    private fun initObserver() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        binding.adCarousel.registerLifecycle(viewLifecycleOwner)

        initViews()
        //initListeners()
        //loadData()
    }

    private fun loadData() {

    }

    private fun initListeners() {

    }

    private fun initViews() {
        val list = mutableListOf<CarouselItem>()

        list.add(
            CarouselItem(
                imageDrawable = R.drawable.furniture
            )
        )

        list.add(
            CarouselItem(
                imageDrawable = R.drawable.furniture_2
            )
        )

        list.add(
            CarouselItem(
                imageDrawable = R.drawable.furniture_3
            )
        )

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

        val featureProductList = mutableListOf<ProductItem>()

        featureProductList.add(
            ProductItem(
                id = 0,
                productImage = R.drawable.fashion_model,
                productName = "Women outfit for show",
                rating = 4.0f,
                price = 15.00
            )
        )

        featureProductList.add(
            ProductItem(
                id = 0,
                productImage = R.drawable.essentials_hoodie,
                productName = "Fear of God",
                rating = 4.5f,
                price = 15.00
            )
        )

        featureProductList.add(
            ProductItem(
                id = 0,
                productImage = R.drawable.max_tshirt,
                productName = "Max 90 t-shirt for men",
                rating = 4.0f,
                price = 15.00
            )
        )
        featureProductList.add(
            ProductItem(
                id = 0,
                productImage = R.drawable.nike_sportwear,
                productName = "Nike Sportswear",
                rating = 4.0f,
                price = 15.00
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




        binding.adCarousel.setData(list)

        binding.bestSellingRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.bestSellingRv.adapter = bestSellingAdapter
        bestSellingAdapter.submitList(bestSellingList)


        binding.categoryRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRv.adapter = categoryAdapter
        categoryAdapter.submitList(categoryList)

        binding.featureProductRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.featureProductRv.adapter = featureProductsAdapter
        featureProductsAdapter.submitList(featureProductList)

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


    }
}