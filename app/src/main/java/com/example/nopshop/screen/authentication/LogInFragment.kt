package com.example.nopshop.screen.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.nopshop.MainActivity
import com.example.nopshop.R
import com.example.nopshop.databinding.ActivityMainBinding


class LogInFragment : Fragment(R.layout.fragment_log_in) {
    private val viewModel:LogInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initObserver(){
        viewModel.response.observe(this){data->
            //TODO Handle ui change here
        }
    }
}