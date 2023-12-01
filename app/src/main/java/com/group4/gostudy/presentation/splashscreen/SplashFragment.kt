package com.group4.gostudy.presentation.splashscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroCustomLayoutFragment
import com.group4.gostudy.R

class SplashFragment : AppIntro() {

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
        finish()
    }
}
