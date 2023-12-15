package com.group4.gostudy.presentation.forgotpassword

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.group4.gostudy.databinding.ActivityResetPasswordBinding
import com.group4.gostudy.utils.ResultWrapper
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResetPasswordBinding
    private val viewModel: ForgotPasswordViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setClickListeners()
    }

    private fun setupViews() {
        // Add any additional setup for views if needed
    }

    private fun setClickListeners() {
        binding.btnReset.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()

            if (isValidEmail(email)) {
                // Panggil metode forgotPassword dari viewModel
                viewModel.forgotPassword(email)
            } else {
                Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show()
            }
        }

        // Amati hasil dari LiveData
        viewModel.forgotPasswordResult.observe(
            this,
            Observer { result ->
                when (result) {
                    is ResultWrapper.Success -> {
                        Toast.makeText(this, "Password reset email sent successfully", Toast.LENGTH_SHORT).show()
                    }
                    is ResultWrapper.Error -> {
                        // Tindakan jika permintaan gagal
                        // Contoh: Tampilkan pesan error kepada pengguna
                        Toast.makeText(this, "Password reset request failed", Toast.LENGTH_SHORT).show()
                    }
                    is ResultWrapper.Empty -> {
                        // Tindakan untuk kasus 'Empty'
                        Toast.makeText(this, "Empty result", Toast.LENGTH_SHORT).show()
                    }
                    else -> { Toast.makeText(this, "Unexpected result", Toast.LENGTH_SHORT).show() }
                }
            }
        )
    }

    private fun isValidEmail(email: String): Boolean {
        // Implement your email validation logic
        // You can use android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() for a basic check
        return true
    }
}
