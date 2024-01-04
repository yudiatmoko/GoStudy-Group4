package com.group4.gostudy.presentation.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.group4.gostudy.data.local.datastore.NonLoginMode
import com.group4.gostudy.databinding.ActivitySplashScreenBinding
import com.group4.gostudy.presentation.login.LoginActivity
import com.group4.gostudy.presentation.main.MainActivity
import com.group4.gostudy.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreen : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    private val binding: ActivitySplashScreenBinding by lazy {
        ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        checkIfBoarding()
    }

    private fun checkIfBoarding() {
        mainViewModel.nonLoginLiveData.observe(this) {
            when (it) {
                NonLoginMode.FIRST_INSTALL.value -> startAppIntro()
                NonLoginMode.LOGIN_AS_GUEST.value -> startMainActivity()
                NonLoginMode.LOGOUT_FROM_GUEST.value -> checkIfUserLogin()
            }
        }
    }

    private fun checkIfUserLogin() {
        mainViewModel.userTokenLiveData.observe(this) {
            if (it.isNullOrBlank()) {
                navigateToLogin()
            } else {
                startMainActivity()
            }
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startAppIntro() {
        val intent = Intent(this, CustomAppIntroActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
