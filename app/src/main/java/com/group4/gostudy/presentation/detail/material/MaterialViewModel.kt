package com.group4.gostudy.presentation.detail.material

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.repository.CourseRepository
import com.group4.gostudy.model.Chapter
import com.group4.gostudy.model.Course
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MaterialViewModel(
    private val courseRepo: CourseRepository
) : ViewModel() {
    private val _courses =
        MutableLiveData<ResultWrapper<Course>>()

    val courses: LiveData<ResultWrapper<Course>>
        get() = _courses
    private val _chapter = MutableLiveData<ResultWrapper<List<Chapter>>>()
    val chapter: LiveData<ResultWrapper<List<Chapter>>>
        get() = _chapter

    fun getChaptersV2(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            courseRepo.getChaptersV2(id).collect {
                _chapter.postValue(it)
            }
        }
    }
}
