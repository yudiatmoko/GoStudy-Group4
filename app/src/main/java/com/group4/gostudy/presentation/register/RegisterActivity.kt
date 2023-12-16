package com.group4.gostudy.presentation.register

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputLayout
import com.group4.gostudy.R
import com.group4.gostudy.data.network.api.model.register.RegisterRequest
import com.group4.gostudy.databinding.ActivityRegisterBinding
import com.group4.gostudy.presentation.login.LoginActivity
import com.group4.gostudy.presentation.main.MainActivity
import com.group4.gostudy.presentation.main.MainViewModel
import com.group4.gostudy.presentation.otp.OtpActivity
import com.group4.gostudy.utils.ApiException
import com.group4.gostudy.utils.highLightWord
import com.group4.gostudy.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    private val viewModel: RegisterViewModel by viewModel()

    private val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setClickListeners()
        observeRegister()
        setupForm()
    }

    private fun setupForm() {
        binding.layoutFormRegister.tilName.isVisible = true
        binding.layoutFormRegister.tilEmail.isVisible = true
        binding.layoutFormRegister.tilPassword.isVisible = true
        binding.layoutFormRegister.tilPhoneNumber.isVisible = true
    }

    private fun observeRegister() {
        viewModel.registerResult.observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.layoutFormRegister.pbLoading.isVisible = false
                    binding.layoutFormRegister.btnRegister.isVisible = true
                    binding.layoutFormRegister.btnRegister.isEnabled = false
                    it.payload?.let { it1 ->
                        mainViewModel.setUserToken(
                            it1
                        )
                    }
                    navigateToOtp()
                },
                doOnLoading = {
                    binding.layoutFormRegister.pbLoading.isVisible = true
                    binding.layoutFormRegister.btnRegister.isVisible = false
                },
                doOnError = {
                    binding.layoutFormRegister.pbLoading.isVisible = false
                    binding.layoutFormRegister.btnRegister.isVisible = true
                    binding.layoutFormRegister.btnRegister.isEnabled = true
                    if (it.exception is ApiException) {
                        Toast.makeText(
                            this,
                            getString(
                                R.string.register_failed,
                                it.exception.getParsedError()?.message
                            ),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
        }
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }

    private fun setClickListeners() {
        binding.tvNavToLogin.highLightWord(
            getString(
                R.string.text_login
            )
        ) {
            navigateToLogin()
        }

        binding.layoutFormRegister.btnRegister.setOnClickListener {
            doRegister()
        }
    }

    private fun doRegister() {
        if (isFormValid()) {
            val name = binding.layoutFormRegister.etName.text.toString().trim()
            val email = binding.layoutFormRegister.etEmail.text.toString().trim()
            val password = binding.layoutFormRegister.etPassword.text.toString().trim()
            val phoneNumber = binding.layoutFormRegister.etPhoneNumber.text.toString().trim()
            viewModel.doRegister(
                RegisterRequest(
                    name,
                    email,
                    password,
                    phoneNumber
                )
            )
        }
    }

    private fun isFormValid(): Boolean {
        val name = binding.layoutFormRegister.etName.text.toString().trim()
        val email = binding.layoutFormRegister.etEmail.text.toString().trim()
        val password = binding.layoutFormRegister.etPassword.text.toString().trim()
        val phoneNumber = binding.layoutFormRegister.etPhoneNumber.text.toString().trim()

        return checkNameValidation(name) &&
            checkEmailValidation(email) &&
            checkPasswordValidation(password, binding.layoutFormRegister.tilPassword) &&
            checkPhoneNumberValidation(phoneNumber)
    }

    private fun checkPhoneNumberValidation(
        phoneNumber: String
    ): Boolean {
        return if (phoneNumber.isEmpty()) {
            binding.layoutFormRegister.tilPhoneNumber.isErrorEnabled = true
            binding.layoutFormRegister.tilPhoneNumber.error = getString(R.string.text_error_phone_cannot_empty)
            false
        } else {
            binding.layoutFormRegister.tilPhoneNumber.isErrorEnabled = false
            true
        }
    }

    private fun checkPasswordValidation(
        password: String,
        textInputLayout: TextInputLayout
    ): Boolean {
        return if (password.isEmpty()) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = getString(R.string.text_error_password_empty)
            false
        } else if (password.length < 8) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = getString(R.string.text_error_password_less_than_8_char)
            false
        } else {
            textInputLayout.isErrorEnabled = false
            true
        }
    }

    private fun checkEmailValidation(email: String): Boolean {
        return if (email.isEmpty()) {
            binding.layoutFormRegister.tilEmail.isErrorEnabled = true
            binding.layoutFormRegister.tilEmail.error = getString(R.string.text_error_email_empty)
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.layoutFormRegister.tilEmail.isErrorEnabled = true
            binding.layoutFormRegister.tilEmail.error = getString(R.string.text_error_email_invalid)
            false
        } else {
            binding.layoutFormRegister.tilEmail.isErrorEnabled = false
            true
        }
    }

    private fun checkNameValidation(name: String): Boolean {
        return if (name.isEmpty()) {
            binding.layoutFormRegister.tilName.isErrorEnabled = true
            binding.layoutFormRegister.tilName.error = getString(R.string.text_error_name_cannot_empty)
            false
        } else {
            binding.layoutFormRegister.tilName.isErrorEnabled = false
            true
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(intent)
    }

    private fun navigateToOtp() {
        val intent = Intent(this, OtpActivity::class.java)
        startActivity(intent)
    }
}
