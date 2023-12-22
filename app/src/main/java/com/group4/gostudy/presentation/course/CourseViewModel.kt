package com.group4.gostudy.presentation.course

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.repository.CourseRepository
import com.group4.gostudy.model.Course
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CourseViewModel(
    private val courseRepository: CourseRepository
) : ViewModel() {

    private val _courses = MutableLiveData<ResultWrapper<List<Course>>>()

    val courses: LiveData<ResultWrapper<List<Course>>>
        get() = _courses

    fun getCourse(category: String? = null, search: String? = null, type: String? = null, level: String? = null, createAt: Boolean? = null, promoPrecentage: Boolean? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            courseRepository.getCourses(category, search, type, level, createAt, promoPrecentage).collect {
                _courses.postValue(it)
            }
        }
    }
}
