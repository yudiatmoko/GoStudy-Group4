package com.group4.gostudy.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.repository.CourseRepository
import com.group4.gostudy.model.Category
import com.group4.gostudy.model.Course
import com.group4.gostudy.model.PopularCourse
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class HomeViewModel(
    private val courseRepository: CourseRepository
) : ViewModel() {

    private val _categories = MutableLiveData<ResultWrapper<List<Category>>>()

    val categories: LiveData<ResultWrapper<List<Category>>>
        get() = _categories

    fun getCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            courseRepository.getCategories().collect {
                _categories.postValue(it)
            }
        }
    }

    private val _courses = MutableLiveData<ResultWrapper<List<Course>>>()

    val courses: LiveData<ResultWrapper<List<Course>>>
        get() = _courses

    fun getCourse(category: String? = null, search: String? = null, type: String? = null, level: String? = null, createAt: Boolean? = null, promoPrecentage: Boolean? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            courseRepository.getCourses(category, search, null, null, null, null).collect {
                _courses.postValue(it)
            }
        }
    }
}
