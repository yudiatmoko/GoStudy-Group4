package com.group4.gostudy.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.group4.gostudy.utils.highLightWord
import com.group4.gostudy.R
import com.group4.gostudy.databinding.ActivityLoginBinding
import com.group4.gostudy.presentation.main.MainActivity
import com.group4.gostudy.presentation.main.MainViewModel
import com.group4.gostudy.presentation.register.RegisterActivity
import com.group4.gostudy.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val viewModel: LoginViewModel by viewModel()

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setClickListeners()
        observeLogin()
        setForm()
    }

    private fun setForm() {
        binding.layoutFormLogin.tilEmail.isVisible = true
        binding.layoutFormLogin.tilPassword.isVisible = true
    }

    private fun observeLogin() {
        viewModel.login.observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.layoutFormLogin.pbLoading.isVisible = false
                    binding.layoutFormLogin.btnLogin.isVisible = true
                    binding.layoutFormLogin.btnLogin.isEnabled = false
                    it.payload?.let { token ->
                        mainViewModel.setUserToken(
                            token
                        )
                    }
                    navigateToMain()
                },
                doOnLoading = {
                    binding.layoutFormLogin.pbLoading.isVisible = true
                    binding.layoutFormLogin.btnLogin.isVisible = false
                },
                doOnError = {
                    binding.layoutFormLogin.pbLoading.isVisible = false
                    binding.layoutFormLogin.btnLogin.isVisible = true
                    binding.layoutFormLogin.btnLogin.isEnabled = true
                    Toast.makeText(
                        this,
                        getString(
                            R.string.login_failed,
                            it.exception?.message.orEmpty()
                        ),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun setClickListeners() {
        binding.tvBelumPunyaAccount.highLightWord(getString(R.string.text_highlight_daftardisini)) {
            navigateToRegister()
        }
        binding.layoutFormLogin.btnLogin.setOnClickListener {
            doLogin()
        }
    }

    private fun navigateToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun doLogin() {
        if (isFormValid()) {
            val email = binding.layoutFormLogin.etEmail.text.toString().trim()
            val password = binding.layoutFormLogin.etPassword.text.toString().trim()
            viewModel.login(email, password)
        }
    }

    private fun isFormValid(): Boolean {
        val email = binding.layoutFormLogin.etEmail.text.toString().trim()
        val password = binding.layoutFormLogin.etPassword.text.toString().trim()
        return checkEmailValidation(email) && checkPasswordValidation(password)
    }

    private fun checkEmailValidation(email: String): Boolean {
        return if (email.isEmpty()) {
            binding.layoutFormLogin.tilEmail.isErrorEnabled = true
            binding.layoutFormLogin.tilEmail.error = getString(R.string.text_error_email_empty)
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.layoutFormLogin.tilEmail.isErrorEnabled = true
            binding.layoutFormLogin.tilEmail.error = getString(R.string.text_error_email_invalid)
            false
        } else {
            binding.layoutFormLogin.tilEmail.isErrorEnabled = false
            true
        }
    }

    private fun checkPasswordValidation(
        password: String
    ): Boolean {
        return if (password.isEmpty()) {
            binding.layoutFormLogin.tilPassword.isErrorEnabled = true
            binding.layoutFormLogin.tilPassword.error = getString(R.string.text_error_password_empty)
            false
        } else if (password.length < 8) {
            binding.layoutFormLogin.tilPassword.isErrorEnabled = true
            binding.layoutFormLogin.tilPassword.error = getString(R.string.text_error_password_less_than_8_char)
            false
        } else {
            binding.layoutFormLogin.tilPassword.isErrorEnabled = false
            true
        }
    }
}
