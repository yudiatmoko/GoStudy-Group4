package com.group4.gostudy.presentation.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.network.api.model.payment.PaymentRequest
import com.group4.gostudy.data.repository.CourseRepository
import com.group4.gostudy.model.Course
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaymentViewModel(private val courseRepo: CourseRepository) : ViewModel() {
    private val _checkoutResult = MutableLiveData<ResultWrapper<Int>>()
    val checkoutResult: LiveData<ResultWrapper<Int>>
        get() = _checkoutResult

    val priceLiveData = MutableLiveData<Double>().apply {
        postValue(0.0)
    }
    val ppnLiveData = MutableLiveData<Double>().apply {
        postValue(0.0)
    }

    fun order() {
        val courseId = course.value?.payload?.id
        viewModelScope.launch(Dispatchers.IO) {
            courseRepo.order(PaymentRequest(courseId = courseId)).collect {
                _checkoutResult.postValue(it)
            }
        }
    }

    private val _course = MutableLiveData<ResultWrapper<Course>>()

    val course: LiveData<ResultWrapper<Course>>
        get() = _course

    fun getCourseById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            courseRepo.getCourseById(id).collect {
                _course.postValue(it)
            }
        }
    }
}
