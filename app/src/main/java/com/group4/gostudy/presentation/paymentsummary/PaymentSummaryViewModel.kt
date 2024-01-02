package com.group4.gostudy.presentation.paymentsummary

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.group4.gostudy.model.Course
import com.group4.gostudy.model.HistoryPayment
import com.group4.gostudy.presentation.payment.PaymentActivity

class PaymentSummaryViewModel(
    private val extras: Bundle?
) : ViewModel() {
    val payment = extras?.getParcelable<HistoryPayment>(PaymentSummaryActivity.EXTRA_HISTORY)
    val course = extras?.getParcelable<Course>(PaymentActivity.EXTRA_PRODUCT)
}
