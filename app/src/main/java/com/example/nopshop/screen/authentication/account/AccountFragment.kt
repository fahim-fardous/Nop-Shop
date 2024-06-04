package com.example.nopshop.screen.authentication.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nopshop.R
import com.example.nopshop.databinding.FragmentAccountBinding


class AccountFragment : Fragment(R.layout.fragment_account) {
    private lateinit var binding: FragmentAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAccountBinding.bind(view)
    }
}