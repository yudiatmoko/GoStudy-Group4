package com.group4.gostudy.presentation.splashscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroCustomLayoutFragment
import com.group4.gostudy.R
import com.group4.gostudy.presentation.login.LoginActivity
import com.group4.gostudy.presentation.main.MainActivity

class CustomAppIntroActivity : AppIntro() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.fragment_splash))
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.intro_splash_2))

        showStatusBar(true)
        setStatusBarColorRes(R.color.white)
        setNavBarColorRes(R.color.seed)
        setProgressIndicator()
    }

    public override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        navigateToHome()
        finish()
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onSlideChanged(oldFragment: Fragment?, newFragment: Fragment?) {
        super.onSlideChanged(oldFragment, newFragment)

        // Check if the new slide is the second slide
        if (newFragment is AppIntroCustomLayoutFragment) {
            // Set click listener for the login button on the second slide
            val btnLogin = newFragment.view?.findViewById<Button>(R.id.btn_login_intro)
            btnLogin?.setOnClickListener {
                startLoginActivity()
            }

            val btnGuest = newFragment.view?.findViewById<Button>(R.id.btn_guest_intro)
            btnGuest?.setOnClickListener {
                navigateToHome()
            }
        }
    }

    private fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
