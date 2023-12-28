package com.group4.gostudy.presentation.classes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.repository.UserCourseRepository
import com.group4.gostudy.model.UserCourse
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClassesViewModel(
    private val courseRepository: UserCourseRepository
) : ViewModel() {

    private val _usercourse = MutableLiveData<ResultWrapper<List<UserCourse>>>()

    val usercourses: LiveData<ResultWrapper<List<UserCourse>>>
        get() = _usercourse

    fun getUserCourses(status: String? = null, search: String? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            courseRepository.getUserCourse(status, search).collect {
                _usercourse.postValue(it)
            }
        }
    }
}
