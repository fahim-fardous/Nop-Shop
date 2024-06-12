package com.example.nopshop.screen.authentication.account

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.nopshop.R
import com.example.nopshop.databinding.FragmentAccountBinding
import com.example.nopshop.utils.NoInternet
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AccountFragment : Fragment(R.layout.fragment_account) {
    private lateinit var binding: FragmentAccountBinding
    private val viewModel: AccountViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences =
            requireActivity().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        initObserver()
    }

    private fun initObserver() {
        viewModel.success.observe(this) {
            findNavController().popBackStack()
        }
        viewModel.login.observe(this) {

        }
        viewModel.orderCount.observe(this) {
            if (it < 10) {
                binding.orderCountTv.text = it.toString()
            } else {
                binding.orderCountTv.text = "10+"
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAccountBinding.bind(view)
        initViews()
        initListeners()
    }

    private fun initViews() {
        val sharedPreferences =
            requireActivity().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("auth_token", "")
        if (token.isNullOrEmpty()) {
            binding.actionTv.text = "Log in"
            Toast.makeText(requireContext(), "You have to login first", Toast.LENGTH_SHORT).show()
            binding.accountLayout.visibility = View.GONE
            binding.warningImg.visibility = View.VISIBLE
        } else {
            binding.actionTv.text = "Log out"
            binding.accountLayout.visibility = View.VISIBLE
            binding.warningImg.visibility = View.GONE
            viewModel.getOrders()
            val firstName = sharedPreferences.getString("firstname", "")
            val lastName = sharedPreferences.getString("lastname", "")
            val email = sharedPreferences.getString("email", "")
            binding.userNameTv.text = "$firstName $lastName"
            binding.mailTv.text = email
        }
    }

    private fun initListeners() {
        if (binding.actionTv.text == "Log in") {
            binding.actionTv.setOnClickListener {
                findNavController().navigate(R.id.action_accountFragment_to_logInFragment)
            }
        } else {
            binding.actionTv.setOnClickListener {
                viewModel.logOut()
            }
        }
    }
}