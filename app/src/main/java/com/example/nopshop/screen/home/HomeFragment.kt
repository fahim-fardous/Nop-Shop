package com.example.nopshop.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nopshop.R
import com.example.nopshop.adapter.BestSellingAdapter
import com.example.nopshop.databinding.FragmentHomeBinding
import com.example.nopshop.model.BestSellingItem
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: BestSellingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //initObserver()

        adapter = BestSellingAdapter {

        }


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

        val bestSellingList = mutableListOf<BestSellingItem>()

        bestSellingList.add(
            BestSellingItem(
                0,
                R.drawable.orange,
                "California Orange 8 Pcs",
                3,
                15.00

            )
        )

        bestSellingList.add(
            BestSellingItem(
                0,
                R.drawable.steak,
                "California Orange 8 Pcs",
                3,
                15.00

            )
        )

        bestSellingList.add(
            BestSellingItem(
                0,
                R.drawable.furniture,
                "California Orange 8 Pcs",
                3,
                15.00

            )
        )

        bestSellingList.add(
            BestSellingItem(
                0,
                R.drawable.furniture_3,
                "California Orange 8 Pcs",
                3,
                15.00

            )
        )

        bestSellingList.add(
            BestSellingItem(
                0,
                R.drawable.furniture_2,
                "California Orange 8 Pcs",
                3,
                15.00

            )
        )

        binding.adCarousel.setData(list)

        binding.bestSellingRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.bestSellingRv.adapter = adapter
        adapter.submitList(bestSellingList)


    }
}