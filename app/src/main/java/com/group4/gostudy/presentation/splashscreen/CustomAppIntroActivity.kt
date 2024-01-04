package com.group4.gostudy.presentation.splashscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroCustomLayoutFragment
import com.group4.gostudy.R
import com.group4.gostudy.data.local.datastore.NonLoginMode
import com.group4.gostudy.presentation.login.LoginActivity
import com.group4.gostudy.presentation.main.MainActivity
import com.group4.gostudy.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CustomAppIntroActivity : AppIntro() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.intro_splash_1))
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.intro_splash_2))

        showStatusBar(true)
        setProgressIndicator()
        isButtonsEnabled = false
        isSkipButtonEnabled = false
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onSlideChanged(oldFragment: Fragment?, newFragment: Fragment?) {
        super.onSlideChanged(oldFragment, newFragment)

        val btnNext = oldFragment?.view?.findViewById<ImageView>(R.id.iv_next)
        btnNext?.setOnClickListener {
            goToNextSlide()
        }

        if (newFragment is AppIntroCustomLayoutFragment) {
            val btnLogin = newFragment.view?.findViewById<Button>(R.id.btn_login_intro)
            btnLogin?.setOnClickListener {
                startLoginActivity()
                finish()
            }

            val btnGuest = newFragment.view?.findViewById<Button>(R.id.btn_guest_intro)
            btnGuest?.setOnClickListener {
                mainViewModel.setNonLogin(NonLoginMode.LOGIN_AS_GUEST.value)
                navigateToHome()
                finish()
            }
        }
    }
}
