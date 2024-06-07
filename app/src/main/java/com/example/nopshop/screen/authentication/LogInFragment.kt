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
import com.example.nopshop.databinding.FragmentLogInBinding
import com.example.nopshop.utils.Constants
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
            Constants.TOKEN = data.Data.Token
            println(Constants.TOKEN)
            editor.putBoolean("isLoggedIn", true)
            editor.putString("email", binding.usernameEmailEt.text.toString().trimMargin())
            editor.apply()
            Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
        viewModel.showMessage.observe(this) { message ->
            if (message.isNotEmpty()) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}