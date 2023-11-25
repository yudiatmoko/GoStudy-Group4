package com.group4.gostudy.presentation.payment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.group4.gostudy.databinding.ActivityPaymentDetailBinding
import com.group4.gostudy.presentation.payment.dialog.DialogSuccessFragment

class PaymentDetailActivity : AppCompatActivity() {
    private val binding: ActivityPaymentDetailBinding by lazy {
        ActivityPaymentDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickListener()
    }

    private fun setClickListener() {
        binding.btnConfirm.setOnClickListener {
            openPopUpDialog()
        }
    }

    private fun openPopUpDialog() {
        DialogSuccessFragment().show(supportFragmentManager,null)
    }
}