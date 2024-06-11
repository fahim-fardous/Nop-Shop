package com.example.nopshop.screen.authentication.account

import android.content.Context
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        viewModel.success.observe(this) {
            findNavController().popBackStack()
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
        } else {
            binding.actionTv.text = "Log out"
        }
    }

    private fun initListeners() {
        if(binding.actionTv.text == "Log in"){
            binding.actionTv.setOnClickListener {
                findNavController().navigate(R.id.action_accountFragment_to_logInFragment)
            }
        }else{
            binding.actionTv.setOnClickListener {
                viewModel.logOut()
            }
        }
    }
    override fun onResume() {
        super.onResume()
        if(!NoInternet.isOnline(requireContext().applicationContext)){
            Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }
}