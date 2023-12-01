package com.group4.gostudy.presentation.splashscreen

import android.os.Bundle
import com.github.appintro.AppIntro
import com.group4.gostudy.R

class SplashActivity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}
