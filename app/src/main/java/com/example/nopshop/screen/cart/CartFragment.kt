package com.example.nopshop.screen.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nopshop.R
import com.example.nopshop.adapter.CartAdapter
import com.example.nopshop.databinding.FragmentCartBinding
import com.example.nopshop.model.CartItem
import com.example.nopshop.model.cart.Item
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart) {
    private lateinit var binding: FragmentCartBinding
    private lateinit var adapter: CartAdapter
    private val viewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CartAdapter(
            { item ->
                removeItem(item)
            },
            { item, quantity ->
                updateItem(item, quantity)
            }
        )

    }

    private fun initObserver() {
        binding.cartRv.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRv.adapter = adapter
        viewModel.cartResponse.observe(viewLifecycleOwner) { item ->
            item.let {
                adapter.submitList(item.Data.Cart.Items)
            }
            binding.subtotalPriceTv.text = item.Data.OrderTotals.SubTotal
            binding.shippingPriceTv.text = item.Data.OrderTotals.Shipping
            binding.totalPriceTv.text = item.Data.OrderTotals.OrderTotal
            binding.itemCountTv.text =
                "${item.Data.Cart.Items.size} ${if (item.Data.Cart.Items.size > 1) " ITEM(S)" else " ITEM"}"
            initViews()
        }
        viewModel.cartUpdateResponse.observe(viewLifecycleOwner) { item ->
            Toast.makeText(requireContext(), "Item Updated", Toast.LENGTH_SHORT).show()
            item.let {
                adapter.submitList(item.Data.Cart.Items)
            }
            binding.subtotalPriceTv.text = item.Data.OrderTotals.SubTotal
            binding.shippingPriceTv.text = item.Data.OrderTotals.Shipping
            binding.totalPriceTv.text = item.Data.OrderTotals.OrderTotal
            binding.itemCountTv.text =
                "${item.Data.Cart.Items.size} ${if (item.Data.Cart.Items.size > 1) " ITEM(S)" else " ITEM"}"
            initViews()
        }
        viewModel.cartRemoveResponse.observe(viewLifecycleOwner) { item ->
            Toast.makeText(requireContext(), "Item Removed", Toast.LENGTH_SHORT).show()
            item.let {
                adapter.submitList(item.Data.Cart.Items)
            }
            binding.subtotalPriceTv.text = item.Data.OrderTotals.SubTotal
            binding.shippingPriceTv.text = item.Data.OrderTotals.Shipping
            binding.totalPriceTv.text = item.Data.OrderTotals.OrderTotal
            binding.itemCountTv.text =
                "${item.Data.Cart.Items.size} ${if (item.Data.Cart.Items.size > 1) " ITEM(S)" else " ITEM"}"
            initViews()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCartBinding.bind(view)
        initObserver()
        initViews()
        initShimmerEffect()
        initListeners()
        loadData()
    }

    private fun initShimmerEffect() {
        binding.scrollView.visibility = View.GONE
        binding.cartItemShimmerLayout.visibility =View.VISIBLE
        binding.cartItemShimmerLayout.startShimmer()
    }

    private fun loadData() {
        viewModel.getCartProducts()
    }

    private fun initListeners() {
        binding.topBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initViews() {
        binding.cartItemShimmerLayout.stopShimmer()
        binding.cartItemShimmerLayout.visibility = View.GONE
        binding.scrollView.visibility = View.VISIBLE

    }

    private fun removeItem(cartItem: Item) {
        viewModel.removeCart(
            item = cartItem,
        )
    }

    private fun updateItem(cartItem: Item, quantity: Int) {
        if (quantity == 0) {
            removeItem(cartItem)
        } else {
            viewModel.updateCart(
                item = cartItem,
                quantity = quantity
            )
        }
    }
}