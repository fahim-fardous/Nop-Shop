package com.example.nopshop.screen.cart

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nopshop.R
import com.example.nopshop.adapter.CartAdapter
import com.example.nopshop.databinding.FragmentCartBinding
import com.example.nopshop.model.CartItem
import com.example.nopshop.model.cart.Item
import com.example.nopshop.utils.NoInternet
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart) {
    private lateinit var binding: FragmentCartBinding
    private lateinit var adapter: CartAdapter
    private val viewModel: CartViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CartAdapter({ item ->
            removeItem(item)
        }, { item, quantity ->
            updateItem(item, quantity)
        })

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
            binding.cartBadge.text = "${item.Data.Cart.Items.size}"
            binding.itemCountTv.text =
                "${item.Data.Cart.Items.size} ${if (item.Data.Cart.Items.size > 1) " ITEM(S)" else " ITEM"}"
            initViews()
        }
        viewModel.cartUpdateResponse.observe(viewLifecycleOwner) { item ->
            Toast.makeText(requireContext(), "Item Updated", Toast.LENGTH_SHORT).show()
            binding.subtotalPriceTv.text = item.Data.OrderTotals.SubTotal
            binding.shippingPriceTv.text = item.Data.OrderTotals.Shipping
            binding.totalPriceTv.text = item.Data.OrderTotals.OrderTotal
            binding.cartBadge.text = "${item.Data.Cart.Items.size}"
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
            binding.cartBadge.text = "${item.Data.Cart.Items.size}"
            binding.itemCountTv.text =
                "${item.Data.Cart.Items.size} ${if (item.Data.Cart.Items.size > 1) " ITEM(S)" else " ITEM"}"
            initViews()
        }
        viewModel.showMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        viewModel.showLoading.observe(viewLifecycleOwner) {
            binding.progressLayout.visibility = if (it) View.VISIBLE else View.GONE
            binding.overlayView.visibility = if (it) View.VISIBLE else View.GONE
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
        binding.cartItemShimmerLayout.visibility = View.VISIBLE
        binding.cartItemShimmerLayout.startShimmer()
    }

    private fun loadData() {
        viewModel.getCartProducts()
    }


    private fun initListeners() {
        binding.topBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.checkoutBtn.setOnClickListener {
            val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
            if (!NoInternet.isOnline(requireContext().applicationContext)) {
                Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT)
                    .show()
            } else if (isLoggedIn) {
                if (adapter.itemCount > 0) {
                    findNavController().navigate(CartFragmentDirections.actionCartFragmentToCheckOutFragment())
                } else {
                    Toast.makeText(
                        requireContext(), "Atleast add one item to proceed", Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                findNavController().navigate(CartFragmentDirections.actionCartFragmentToLogInFragment())
            }
        }
        binding.overlayView.setOnTouchListener { _, event ->
            event.action == MotionEvent.ACTION_DOWN
        }

    }

    private fun initViews() {
        binding.cartItemShimmerLayout.stopShimmer()
        binding.cartItemShimmerLayout.visibility = View.GONE
        binding.scrollView.visibility = View.VISIBLE
    }

    private fun removeItem(cartItem: Item) {
        getDialog(cartItem)
    }

    private fun getDialog(cartItem: Item) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Do you want to remove this item?")
            .setNeutralButton("Cancel") { dialog, which ->
                // Respond to neutral button press
            }
            .setPositiveButton("Yes") { dialog, which ->
                viewModel.removeCart(cartItem)
            }
            .show()
    }

    private fun updateItem(cartItem: Item, quantity: Int) {
        viewModel.updateCart(
            item = cartItem, quantity = quantity
        )
    }

    override fun onResume() {
        super.onResume()
        if (!NoInternet.isOnline(requireContext().applicationContext)) {
            Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }
}