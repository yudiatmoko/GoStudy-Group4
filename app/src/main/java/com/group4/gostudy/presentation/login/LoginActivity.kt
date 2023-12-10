package com.group4.gostudy.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.catnip.rizkyilmann_challange4.utils.highLightWord
import com.group4.gostudy.R
import com.group4.gostudy.databinding.ActivityLoginBinding
import com.group4.gostudy.presentation.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListeners()
    }

    private fun setClickListeners() {
        binding.tvBelumpunyaAccount.highLightWord(getString(R.string.text_highlight_daftardisini)) {
            navigateToRegister()
        }
    }

    private fun navigateToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}
