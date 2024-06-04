package com.example.nopshop.screen.authentication

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
import com.example.nopshop.MainActivity
import com.example.nopshop.R
import com.example.nopshop.databinding.ActivityMainBinding
import com.example.nopshop.databinding.FragmentLogInBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LogInFragment : Fragment(R.layout.fragment_log_in) {
    private val viewModel: LogInViewModel by viewModels()
    private lateinit var binding: FragmentLogInBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences =
            requireActivity().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        initObserver()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLogInBinding.bind(view)
        //initViews()
        initListeners()
    }

    private fun initListeners() {
        binding.loginBtn.setOnClickListener {
            println("Asteche")
            viewModel.postLogin(
                binding.usernameEmailEt.text.toString().trimMargin(),
                binding.passwordEt.text.toString().trimMargin()
            )
        }
    }

    private fun initViews() {

    }

    private fun initObserver() {
        viewModel.response.observe(this) { data ->
            val editor = sharedPreferences.edit()
            editor.putString("auth_token", data.Data.Token)
            println(data.Data.Token)
            editor.putBoolean("isLoggedIn", true)
            editor.apply()
            println(sharedPreferences.getBoolean("isLoggedIn", false))
            Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
            findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToHomeFragment2())
        }
        viewModel.showMessage.observe(this) { message ->
            if (message.isNotEmpty()) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}