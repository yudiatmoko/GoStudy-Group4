package com.group4.gostudy.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.group4.gostudy.R
import com.group4.gostudy.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setBottomNav()
        hideMenuNonLogin()

        // Membaca flag untuk membuka HomeFragment
        if (intent.getBooleanExtra(EXTRA_OPEN_HOME_FRAGMENT, false)) {
            navigateToHomeFragment()
        }
    }

    private fun hideMenuNonLogin() {
        lifecycleScope.launch {
            val userToken = mainViewModel.getUserToken()
            if (userToken.isNullOrBlank()) {
                binding.bottomNavigationView.isVisible = true
                binding.bottomNavigationView.menu.findItem(R.id.notificationFragment).isVisible = false
                binding.bottomNavigationView.menu.findItem(R.id.classesFragment).isVisible = false
            } else {
                binding.bottomNavigationView.isVisible = true
            }
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
