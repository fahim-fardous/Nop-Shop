package com.example.nopshop.screen.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nopshop.R
import com.example.nopshop.adapter.CartAdapter
import com.example.nopshop.databinding.FragmentCartBinding
import com.example.nopshop.model.CartItem


class CartFragment : Fragment(R.layout.fragment_cart) {
    private lateinit var binding: FragmentCartBinding
    private lateinit var adapter: CartAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CartAdapter {

        }
        //initObserver()
    }

    private fun initObserver() {
        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCartBinding.bind(view)

        initViews()
        initListeners()
        //loadData()
    }

    private fun loadData() {
        TODO("Not yet implemented")
    }

    private fun initListeners() {
        binding.topBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initViews() {
        val list = mutableListOf<CartItem>()

        list.add(
            CartItem(
                id = 0,
                productImage = R.drawable.mobile,
                productName = "Iphone 12",
                productQuantity = 1,
                originalPrice = 599.0,
                discountPrice = 699.0
            )
        )

        list.add(
            CartItem(
                id = 0,
                productImage = R.drawable.steak,
                productName = "Beef Rib Steak",
                productQuantity = 1,
                originalPrice = 599.0,
                discountPrice = 699.0
            )
        )
        list.add(
            CartItem(
                id = 0,
                productImage = R.drawable.mobile,
                productName = "Iphone 12",
                productQuantity = 1,
                originalPrice = 599.0,
                discountPrice = 699.0
            )
        )

        list.add(
            CartItem(
                id = 0,
                productImage = R.drawable.steak,
                productName = "Beef Rib Steak",
                productQuantity = 1,
                originalPrice = 599.0,
                discountPrice = 699.0
            )
        )
        list.add(
            CartItem(
                id = 0,
                productImage = R.drawable.mobile,
                productName = "Iphone 12",
                productQuantity = 1,
                originalPrice = 599.0,
                discountPrice = 699.0
            )
        )

        list.add(
            CartItem(
                id = 0,
                productImage = R.drawable.steak,
                productName = "Beef Rib Steak",
                productQuantity = 1,
                originalPrice = 599.0,
                discountPrice = 699.0
            )
        )
        list.add(
            CartItem(
                id = 0,
                productImage = R.drawable.mobile,
                productName = "Iphone 12",
                productQuantity = 1,
                originalPrice = 599.0,
                discountPrice = 699.0
            )
        )

        list.add(
            CartItem(
                id = 0,
                productImage = R.drawable.steak,
                productName = "Beef Rib Steak",
                productQuantity = 1,
                originalPrice = 599.0,
                discountPrice = 699.0
            )
        )
        binding.itemCountTv.text = "${list.size} ITEM (S)"
        binding.cartRv.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRv.adapter = adapter
        adapter.submitList(list)
    }
}