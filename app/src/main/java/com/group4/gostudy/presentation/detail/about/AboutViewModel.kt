package com.group4.gostudy.presentation.detail.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.group4.gostudy.data.repository.CourseRepository
import com.group4.gostudy.model.Course
import com.group4.gostudy.utils.ResultWrapper

class AboutViewModel(
    private val courseRepo: CourseRepository

) : ViewModel() {

    private val _course = MutableLiveData<ResultWrapper<List<Course>>>()
    val course: LiveData<ResultWrapper<List<Course>>>
        get() = _course

   /* fun getcourseId() {
        viewModelScope.launch(Dispatchers.IO) {
            courseRepo.getCourseById().collect {
                _course.postValue(it)
            }
        }
    }*/
}
