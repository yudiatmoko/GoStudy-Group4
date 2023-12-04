package com.group4.gostudy.presentation.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroCustomLayoutFragment
import com.group4.gostudy.R
import com.group4.gostudy.presentation.home.HomeFragment
import com.group4.gostudy.presentation.main.MainActivity

class SplashActivity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.fragment_splash))
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.intro_splash_2))

        showStatusBar(true)
        setStatusBarColorRes(R.color.white)
        setNavBarColorRes(R.color.seed)
        setProgressIndicator()
    }

    public override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        finish()
    }

    public override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        navigateToHome()
        finish()
    }

    private fun navigateToHome() {
        startActivity(
            Intent(this, HomeFragment::class.java)
        )
    }
}
