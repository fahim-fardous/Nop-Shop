package com.example.nopshop

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.nopshop.databinding.ActivityMainBinding
import com.example.nopshop.screen.authentication.LogInFragment
import com.example.nopshop.screen.category.CategoryFragment
import com.example.nopshop.screen.home.HomeFragment
import com.example.nopshop.utils.Value
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavBar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.logInFragment -> {
                    println(sharedPreferences.getBoolean("isLoggedIn", false))
                    if (sharedPreferences.getBoolean("isLoggedIn", false)) {
                        navController.navigate(R.id.accountFragment)
                        binding.bottomNavBar.visibility = View.VISIBLE
                    } else {
                        binding.bottomNavBar.visibility = View.GONE
                    }
                }
                else -> binding.bottomNavBar.visibility = View.VISIBLE
            }
        }
    }
}