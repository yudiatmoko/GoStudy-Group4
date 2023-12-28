package com.group4.gostudy.presentation.detail.material.dialog

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.repository.CourseRepository
import com.group4.gostudy.model.Course
import com.group4.gostudy.presentation.detail.DetailCourseActivity
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DialogOrderViewModel(
    private val courseRepo: CourseRepository,
    private val extras: Bundle?
) : ViewModel() {
    val course = extras?.getParcelable<Course>(DetailCourseActivity.EXTRA_PRODUCT)

    var idCourse: Int? = course?.id

    private val _courses =
        MutableLiveData<ResultWrapper<Course>>()

    val courses: LiveData<ResultWrapper<Course>>
        get() = _courses

    fun getCourseById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            courseRepo.getCourseById(id).collect {
                _courses.postValue(it)
            }
        }
    }
}
