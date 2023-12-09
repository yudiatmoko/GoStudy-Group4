package com.group4.gostudy.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.group4.gostudy.R
import com.group4.gostudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setBottomNav()

        // Membaca flag untuk membuka HomeFragment
        if (intent.getBooleanExtra(EXTRA_OPEN_HOME_FRAGMENT, false)) {
            navigateToHomeFragment()
        }
    }

    private fun setBottomNav() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)
    }

    private fun navigateToHomeFragment() {
        val navController = findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.homeFragment)
    }

    companion object {
        const val EXTRA_OPEN_HOME_FRAGMENT = "extra_open_home_fragment"
    }
}
