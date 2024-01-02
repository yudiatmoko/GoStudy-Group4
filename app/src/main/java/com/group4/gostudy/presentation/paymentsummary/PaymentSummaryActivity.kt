package com.group4.gostudy.presentation.paymentsummary

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.group4.gostudy.databinding.ActivityPaymentSummaryBinding
import com.group4.gostudy.model.HistoryPayment
import com.group4.gostudy.utils.convertDateTime
import com.group4.gostudy.utils.toCurrencyFormat
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PaymentSummaryActivity : AppCompatActivity() {
    private val binding: ActivityPaymentSummaryBinding by lazy {
        ActivityPaymentSummaryBinding.inflate(layoutInflater)
    }
    private val viewModel: PaymentSummaryViewModel by viewModel { parametersOf(intent.extras) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickListener()
        setDataHistory()
    }

    private fun setDataHistory() {
        viewModel.payment.let {
            binding.layoutContent.ivCourseImg.load(it?.course?.imageUrl)
            binding.layoutContent.tvCategoryName.text = it?.course?.category?.name
            binding.layoutContent.tvCourseName.text = it?.course?.name
            binding.layoutContent.tvCreatorName.text = it?.course?.courseBy
            binding.tvPrice.text = it?.price?.toDouble()?.toCurrencyFormat()
            val ppn: Double? = it?.price?.times(0.11)
            val totalPrice: Double? = (it?.price?.toDouble())?.plus(ppn ?: 0.0)
            binding.tvPpn.text = ppn?.toCurrencyFormat()
            binding.tvTotalPrice.text = totalPrice?.toCurrencyFormat()
            binding.tvOrderId.text = it?.orderId
            binding.tvStatus.apply {
                if (it?.status == "paid") {
                    binding.tvStatus.isVisible = true
                    binding.tvStatusUnpaid.isVisible = false
                    binding.tvStatusExpired.isVisible = false
                } else if (it?.status == "unpaid") {
                    binding.tvStatus.isVisible = false
                    binding.tvStatusUnpaid.isVisible = true
                    binding.tvStatusExpired.isVisible = false
                } else {
                    binding.tvStatus.isVisible = false
                    binding.tvStatusUnpaid.isVisible = false
                    binding.tvStatusExpired.isVisible = true
                }
            }
            binding.tvTime.text = it?.createdAt.convertDateTime()
        }
    }

    private fun setClickListener() {
        binding.tbExit.setOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        const val EXTRA_HISTORY = "EXTRA_HISTORY"

        fun startActivity(context: Context, payment: HistoryPayment?) {
            val intent = Intent(context, PaymentSummaryActivity::class.java)
            intent.putExtra(EXTRA_HISTORY, payment)
            context.startActivity(intent)
        }
    }
}
