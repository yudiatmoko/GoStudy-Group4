package com.group4.gostudy.presentation.payment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.group4.gostudy.R
import com.group4.gostudy.databinding.ActivityPaymentBinding
import com.group4.gostudy.model.Course
import com.group4.gostudy.utils.ApiException
import com.group4.gostudy.utils.proceedWhen
import com.group4.gostudy.utils.toCurrencyFormat
import org.koin.androidx.viewmodel.ext.android.viewModel

class PaymentActivity : AppCompatActivity() {
    private val binding: ActivityPaymentBinding by lazy {
        ActivityPaymentBinding.inflate(layoutInflater)
    }
    private val paymentViewModel: PaymentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickListener()
        observePayment()
    }

    private fun observePayment() {
        paymentViewModel.getCourse()
        paymentViewModel.courses.observe(this) {
            it.proceedWhen(
                doOnLoading = {
                    binding.layoutStatePayment.root.isVisible =
                        true
                    binding.layoutStatePayment.animLoading.isVisible =
                        true
                    binding.layoutStatePayment.llAnimError.isVisible =
                        false
                    binding.cvItemOrder.isVisible =
                        false
                    binding.btnJoinClass.isVisible = false
                },
                doOnSuccess = { result ->
                    binding.layoutStatePayment.root.isVisible =
                        true
                    binding.layoutStatePayment.animLoading.isVisible =
                        false
                    binding.layoutStatePayment.llAnimError.isVisible =
                        false
                    binding.cvItemOrder.isVisible =
                        true
                    binding.btnJoinClass.isVisible = true
                    result.payload?.let { itemOrder ->
                        setData(itemOrder)
                    }
                },
                doOnError = {
                    binding.layoutStatePayment.root.isVisible =
                        true
                    binding.layoutStatePayment.animLoading.isVisible =
                        false
                    binding.layoutStatePayment.llAnimError.isVisible =
                        true
                    binding.cvItemOrder.isVisible =
                        false
                    binding.btnJoinClass.isVisible = false
                    if (it.exception is ApiException) {
                        binding.layoutStatePayment.tvError.isVisible =
                            true
                        binding.layoutStatePayment.tvError.text =
                            it.exception.getParsedError()?.message
                    }
                },
                doOnEmpty = {
                    binding.layoutStatePayment.root.isVisible =
                        true
                    binding.layoutStatePayment.animLoading.isVisible =
                        false
                    binding.layoutStatePayment.tvError.isVisible =
                        true
                    binding.layoutStatePayment.llAnimError.isVisible =
                        true
                    binding.cvItemOrder.isVisible =
                        false
                    binding.layoutStatePayment.tvError.text =
                        getString(R.string.text_no_data)
                    if (it.exception is ApiException) {
                        binding.layoutStatePayment.tvError.text =
                            it.exception.getParsedError()?.message
                    }
                }
            )
        }
        paymentViewModel.priceLiveData.observe(this) {
            binding.tvTotalPrice.text = it.toCurrencyFormat()
        }
        paymentViewModel.ppnLiveData.observe(this) {
            binding.tvPpn.text = it.toCurrencyFormat()
        }
    }
    private fun setData(item: List<Course>) {
        if (item.isNotEmpty()) {
            val courses = item[0]
            binding.layoutContent.ivCourseImg.load(courses.imageUrl)
            binding.layoutContent.tvCategoryName.text = courses.category?.name
            binding.layoutContent.tvCourseName.text = courses.name
            binding.layoutContent.tvCreatorName.text = courses.courseBy
            binding.tvPrice.text = courses.price.toString()
        }
    }

    private fun setClickListener() {
        binding.btnJoinClass.setOnClickListener {
            navigateToMidtrans()
        }
        binding.toolbar.setOnClickListener {
            onBackPressed()
        }
    }

    private fun navigateToMidtrans() {
        val intent = Intent(this, MidtransWebView::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        intent.putExtra(
            "URL",
            "https://app.sandbox.midtrans.com/snap/v3/redirection/64cb51b7-75ee-4b93-b6f6-268426a47264"
        )
        startActivity(intent)
    }
}
