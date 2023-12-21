package com.group4.gostudy.presentation.detail.material.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.repository.CourseRepository
import com.group4.gostudy.model.PopularCourse
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DialogOrderViewModel(private val orderRepo: CourseRepository) : ViewModel() {
    private val _courses =
        MutableLiveData<ResultWrapper<List<PopularCourse>>>()

    val courses: LiveData<ResultWrapper<List<PopularCourse>>>
        get() = _courses

    fun getCourse(
        category: String? = null,
        search: String? = null,
        type: String? = null,
        level: String? = null,
        promoPrecentage: Boolean? = null,
        createAt: Boolean? = null
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            orderRepo.getCourses(
                category,
                search,
                type,
                level,
                promoPrecentage,
                createAt
            ).collect {
                _courses.postValue(it)
            }
        }
    }
}
