package com.group4.gostudy.presentation.payment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.group4.gostudy.databinding.ActivityPaymentBinding
import com.group4.gostudy.model.Course
import com.group4.gostudy.utils.ApiException
import com.group4.gostudy.utils.proceedWhen
import com.group4.gostudy.utils.toCurrencyFormat
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PaymentActivity : AppCompatActivity() {
    private val binding: ActivityPaymentBinding by lazy {
        ActivityPaymentBinding.inflate(layoutInflater)
    }
    private val paymentViewModel: PaymentViewModel by viewModel { parametersOf(intent?.extras) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickListener()
        bindProduct()
        observeCheckoutResult()
    }

    private fun bindProduct() {
        paymentViewModel.courses.let {
            binding.layoutContent.ivCourseImg.load(it?.imageUrl)
            binding.layoutContent.tvCategoryName.text = it?.category?.name
            binding.layoutContent.tvCourseName.text = it?.name
            binding.layoutContent.tvCreatorName.text = it?.courseBy
            binding.tvPrice.text = it?.price?.toDouble()?.toCurrencyFormat()
            val ppn: Double? = it?.price?.times(0.11)
            val totalPrice: Double? = (it?.price?.toDouble())?.plus(ppn ?: 0.0)
            binding.tvPpn.text = ppn?.toCurrencyFormat()
            binding.tvTotalPrice.text = totalPrice?.toCurrencyFormat()
        }
    }

    private fun observeCheckoutResult() {
        paymentViewModel.checkoutResult.observe(this) {
            it.proceedWhen(
                doOnSuccess = { result ->
                    binding.layoutStatePayment.root.isVisible = false
                    binding.layoutStatePayment.animLoading.isVisible = false
                    binding.layoutContent.root.isVisible = true
                    binding.cvItemOrder.isVisible = true
                    binding.btnJoinClass.setOnClickListener {
                        navigateToPaymentWeb(result.payload?.redirectUrl.toString())
                    }
                },
                doOnError = {
                    binding.layoutStatePayment.root.isVisible = false
                    binding.layoutStatePayment.animLoading.isVisible = false
                    if (it.exception is ApiException) {
                        it.exception.getParsedError()?.message
                    }
                },
                doOnLoading = {
                    binding.layoutStatePayment.root.isVisible = true
                    binding.layoutStatePayment.animLoading.isVisible = true
                }
            )
        }
    }

    private fun setClickListener() {
        binding.toolbar.setOnClickListener {
            onBackPressed()
        }
    }

    private fun navigateToPaymentWeb(urlPayment: String) {
        val intent = Intent(this, PaymentWeb::class.java)
        intent.putExtra("URL", urlPayment)
        startActivity(intent)
    }

    companion object {
        const val EXTRA_PRODUCT = "EXTRA_PRODUCT"
        fun startActivity(context: Context, course: Course?) {
            val intent = Intent(context, PaymentActivity::class.java)
            intent.putExtra(EXTRA_PRODUCT, course)
            context.startActivity(intent)
        }
    }
}
