package com.example.nopshop

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nopshop.databinding.ActivityMainBinding
import com.example.nopshop.screen.authentication.LogInFragment
import com.example.nopshop.screen.category.CategoryFragment
import com.example.nopshop.screen.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.bottomNavBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    HomeFragment()
                    true
                }

                R.id.categories -> {
                    CategoryFragment()
                    true
                }

                R.id.search -> {
                    true
                }

                R.id.account -> {
                    LogInFragment()
                    true
                }

                R.id.more -> {
                    true
                }
                else -> false
            }
        }


    }
}