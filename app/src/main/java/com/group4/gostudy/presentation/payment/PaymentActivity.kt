package com.group4.gostudy.presentation.payment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.group4.gostudy.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {
    private val binding: ActivityPaymentBinding by lazy {
        ActivityPaymentBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickListener()
    }

    private fun setClickListener() {
        binding.btnJoinClass.setOnClickListener {
            navigateToPaymentDetail()
        }
    }

    private fun navigateToPaymentDetail() {
        val intent = Intent(this, PaymentDetailActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }
}