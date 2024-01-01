package com.group4.gostudy.presentation.detail

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.repository.UserCourseRepository
import com.group4.gostudy.model.Course
import com.group4.gostudy.model.Module
import com.group4.gostudy.model.UserCourse
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(
    private val extras: Bundle?,
    private val userCourseRepository: UserCourseRepository

) : ViewModel() {
    val course = extras?.getParcelable<Course>(DetailCourseActivity.EXTRA_PRODUCT)

    val userCourse = extras?.getParcelable<UserCourse>(DetailCourseActivity.EXTRA_USER_COURSE)

    var idCourse: Int? = course?.id

    private val _moduleVideo = MutableLiveData<ResultWrapper<Module>>()
    val moduleVideo: LiveData<ResultWrapper<Module>>
        get() = _moduleVideo

    fun getModuleVideo(courseId: Int, moduleId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            userCourseRepository.getModuleVideoById(courseId, moduleId).collect {
                _moduleVideo.postValue(it)
            }
        }
    }
    private val _videoId = MutableLiveData<String>()
    val videoId: LiveData<String> get() = _videoId
    fun getContentUrl(videoUrl: String) {
        _videoId.postValue(videoUrl)
    }
}
