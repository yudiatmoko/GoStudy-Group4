package com.group4.gostudy.presentation.otp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.group4.gostudy.R
import com.group4.gostudy.data.network.api.model.otp.OtpRequest
import com.group4.gostudy.databinding.ActivityOtpBinding
import com.group4.gostudy.presentation.main.MainActivity
import com.group4.gostudy.utils.highLightWord
import com.group4.gostudy.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class OtpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpBinding
    private val viewModel: OtpViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListeners()
        observeVerifyResult()
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun doVerify() {
        val otp = binding.otpView.otp
        viewModel.verifyResult(OtpRequest(otp))
    }

    private fun observeVerifyResult() {
        viewModel.verify.observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.pbLoading.isVisible = false
                    binding.btnSubmit.isVisible = true
                    binding.btnSubmit.isEnabled = false
                    Toast.makeText(
                        this,
                        getString(
                            R.string.verify_success,
                            it.payload.orEmpty()
                        ),
                        Toast.LENGTH_SHORT
                    ).show()
                    navigateToMain()
                },
                doOnLoading = {
                    binding.pbLoading.isVisible = true
                    binding.btnSubmit.isVisible = false
                },
                doOnError = {
                    binding.pbLoading.isVisible = false
                    binding.btnSubmit.isVisible = true
                    binding.btnSubmit.isEnabled = true
                    Toast.makeText(
                        this,
                        getString(
                            R.string.verify_failed,
                            it.payload.orEmpty()
                        ),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }
    }

    private fun setClickListeners() {
        binding.tvResendOtp.highLightWord(
            getString(R.string.text_minta_kode_baru_via_email)
        ) {
            viewModel.resendOtp()
            Toast.makeText(this, "Resending OTP...", Toast.LENGTH_SHORT).show()
        }
        binding.btnSubmit.setOnClickListener {
            doVerify()
        }
    }
}
