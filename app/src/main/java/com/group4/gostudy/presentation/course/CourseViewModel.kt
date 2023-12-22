package com.group4.gostudy.presentation.course

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.repository.CourseRepository
import com.group4.gostudy.model.PopularCourse
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CourseViewModel(private val courseRepository: CourseRepository) : ViewModel() {

    private val _courses = MutableLiveData<ResultWrapper<List<PopularCourse>>>()

    val courses: LiveData<ResultWrapper<List<PopularCourse>>>
        get() = _courses

    fun getCourse(
        category: List<String>? = null,
        search: String? = null,
        type: String? = null,
        levels: List<String>? = null,
        createAt: Boolean? = null,
        promoPrecentage: Boolean? = null
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val levelList = levels?.joinToString(",")
            val categoryList = category?.joinToString(",")
            courseRepository.getCourses(
                categoryList,
                search,
                type,
                levelList,
                createAt,
                promoPrecentage
            ).collect {
                _courses.postValue(it)
            }
        }
    }
}
