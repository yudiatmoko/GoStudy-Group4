package com.group4.gostudy.presentation.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.group4.gostudy.presentation.main.MainActivity
import com.group4.gostudy.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreen : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.userTokenLiveData.observe(this) {
            if (it != "") {
                startMainActivity()
            } else {
                startAppIntro()
            }
        }
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
