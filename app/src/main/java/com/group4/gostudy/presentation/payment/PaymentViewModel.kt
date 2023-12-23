package com.group4.gostudy.presentation.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.repository.CourseRepository
import com.group4.gostudy.model.Course
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaymentViewModel(private val courseRepository: CourseRepository) : ViewModel() {
    val priceLiveData = MutableLiveData<Double>().apply {
        postValue(0.0)
    }
    val ppnLiveData = MutableLiveData<Double>().apply {
        postValue(0.0)
    }
    private val _courses = MutableLiveData<ResultWrapper<List<Course>>>()

    val courses: LiveData<ResultWrapper<List<Course>>>
        get() = _courses
    fun getCourse(category: String? = null, search: String? = null, type: String? = null, level: String? = null, createAt: Boolean? = null, promoPrecentage: Boolean? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            courseRepository.getCourses(category, search, type, level, null, null, null).collect {
                _courses.postValue(it)
            }
        }
    }
}
