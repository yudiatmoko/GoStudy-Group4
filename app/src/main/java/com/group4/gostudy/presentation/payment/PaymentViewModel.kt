package com.group4.gostudy.presentation.payment

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.network.api.model.payment.PaymentRequest
import com.group4.gostudy.data.repository.CourseRepository
import com.group4.gostudy.model.Course
import com.group4.gostudy.model.Payment
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaymentViewModel(
    private val extras: Bundle?,
    private val courseRepo: CourseRepository
) : ViewModel() {
    val course = extras?.getParcelable<Course>(PaymentActivity.EXTRA_PRODUCT)

    private val _checkoutResult = MutableLiveData<ResultWrapper<Payment>>()
    val checkoutResult: LiveData<ResultWrapper<Payment>>
        get() = _checkoutResult

    init {
        order(PaymentRequest(course?.id))
    }
    fun order(paymentRequest: PaymentRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            courseRepo.order(paymentRequest).collect {
                _checkoutResult.postValue(it)
            }
        }
    }
}
