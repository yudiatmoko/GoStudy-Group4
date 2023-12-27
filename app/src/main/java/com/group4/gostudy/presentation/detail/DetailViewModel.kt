package com.group4.gostudy.presentation.detail

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.repository.UserCourseRepository
import com.group4.gostudy.model.Course
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(
    private val extras: Bundle?,
    private val userCourseRepository: UserCourseRepository

) : ViewModel() {
    val course = extras?.getParcelable<Course>(DetailCourseActivity.EXTRA_PRODUCT)

    var idCourse: Int? = course?.id

    private val _detail = MutableLiveData<ResultWrapper<Course?>>()

    val details: LiveData<ResultWrapper<Course?>>
        get() = _detail

    fun getDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            userCourseRepository.getUserCourseById(idCourse ?: 0).collect {
                _detail.postValue(it)
            }
        }
    }

    /*    fun getDetails(category: String? = null, module: String? = null, chapter: String? = null) {
            viewModelScope.launch(Dispatchers.IO) {
                detailRepository.getCourseID(category, module,chapter).collect {
                    _detail.postValue(it)
                }
            }
        }*/
}
