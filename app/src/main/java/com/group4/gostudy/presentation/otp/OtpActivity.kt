package com.group4.gostudy.presentation.otp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.group4.gostudy.R
import com.group4.gostudy.databinding.ActivityOtpBinding
import com.group4.gostudy.presentation.main.MainActivity
import com.group4.gostudy.utils.highLightWord
import org.koin.androidx.viewmodel.ext.android.viewModel

class OtpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpBinding
    private val viewModel: OtpViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setClickListeners()
    }

    private fun setupViews() {
        binding.tvResendOtp.highLightWord(
            getString(R.string.text_minta_kode_baru_via_email)
        ) {
            viewModel.resendOtp()
        }
    }

    private fun setClickListeners() {
        binding.tvResendOtp.setOnClickListener {
            // Handle click on tvResendOtp
            viewModel.resendOtp()
            // You can also show a toast or perform other actions as needed
            Toast.makeText(this, "Resending OTP...", Toast.LENGTH_SHORT).show()
        }

        binding.btnSubmitOtp.setOnClickListener {
            // Assume that isOtpValid is a function to check if the entered OTP is valid
            if (isOtpValid()) {
                // Navigate to home (replace HomeActivity::class.java with your actual home activity)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Optional: finish current activity
            } else {
                Toast.makeText(this, "Invalid OTP. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isOtpValid(): Boolean {
        return true
    }
}
