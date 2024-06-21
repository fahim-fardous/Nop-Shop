package com.example.nopshop

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.nopshop.databinding.ActivityMainBinding
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

        binding.bottomNavBar.setOnItemSelectedListener {
            val popped = navController.popBackStack(it.itemId, false)
            if (!popped) {
                navController.navigate(it.itemId)
            }

            true
        }

        /*navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavBar.visibility = if (
                destination.id == R.id.homeFragment ||
                destination.id == R.id.categoryFragment ||
                destination.id == R.id.searchFragment ||
                destination.id == R.id.accountFragment ||
                destination.id == R.id.moreFragment2
            ) View.VISIBLE else View.GONE
        }*/
    }
}