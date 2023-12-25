package com.group4.gostudy.presentation.payment

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.group4.gostudy.R
import com.group4.gostudy.databinding.ActivityPaymentBinding
import com.group4.gostudy.model.Course
import com.group4.gostudy.presentation.detail.DetailViewModel
import com.group4.gostudy.utils.ApiException
import com.group4.gostudy.utils.proceedWhen
import com.group4.gostudy.utils.toCurrencyFormat
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PaymentActivity : AppCompatActivity() {
    private val binding: ActivityPaymentBinding by lazy {
        ActivityPaymentBinding.inflate(layoutInflater)
    }
    private val paymentViewModel: PaymentViewModel by viewModel()
    private val detailViewModel: DetailViewModel by viewModel { parametersOf(intent?.extras) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickListener()
        observePayment()
        observeCheckoutResult()
    }

    private fun observeCheckoutResult() {
        paymentViewModel.checkoutResult.observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.layoutStatePayment.root.isVisible = false
                    binding.layoutStatePayment.animLoading.isVisible = false
                    navigateToPaymentWeb("https://app.sandbox.midtrans.com/snap/v3/redirection/")
                },
                doOnError = {
                    binding.layoutStatePayment.root.isVisible = false
                    binding.layoutStatePayment.animLoading.isVisible = false
                    Toast.makeText(this, "Checkout Error", Toast.LENGTH_SHORT).show()
                },
                doOnLoading = {
                    binding.layoutStatePayment.root.isVisible = true
                    binding.layoutStatePayment.animLoading.isVisible = true
                }
            )
        }
    }

    private fun observePayment() {
        detailViewModel.idCourse?.let {
            paymentViewModel.getCourseById(
                it
            )
        }
        paymentViewModel.course.observe(this) {
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
                    result.payload?.let {
                        setData(it)
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

    private fun setData(course: Course?) {
        course?.let {
            binding.layoutContent.ivCourseImg.load(course.imageUrl)
            binding.layoutContent.tvCategoryName.text = course.category?.name
            binding.layoutContent.tvCourseName.text = course.name
            binding.layoutContent.tvCreatorName.text = course.courseBy
            binding.tvPrice.text = course.price.toString()
        }
    }

    private fun setClickListener() {
        binding.btnJoinClass.setOnClickListener {
            paymentViewModel.order()
        }
        binding.toolbar.setOnClickListener {
            onBackPressed()
        }
    }

    private fun navigateToPaymentWeb(urlPayment: String) {
        val intent = Intent(this, PaymentWeb::class.java)
        intent.putExtra("URL", urlPayment)
        startActivity(intent)
    }
}
