package com.group4.gostudy.presentation.detail

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.group4.gostudy.data.repository.DetailRepository
import com.group4.gostudy.model.Course

class DetailViewModel(
    private val extras: Bundle?,
    private val detailRepository: DetailRepository

) : ViewModel() {
    val course = extras?.getParcelable<Course>(DetailCourseActivity.EXTRA_PRODUCT)

    var idCourse: Int? = course?.id

  /*  private val _detail = MutableLiveData<ResultWrapper<List<PopularCourse>>>()

    val details: LiveData<ResultWrapper<List<DetailCourse>>>
        get() = _detail

    fun getDetails(category: String? = null, module: String? = null, chapter: String? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            detailRepository.getCourseID(category, module,chapter).collect {
                _detail.postValue(it)
            }
        }
    }*/
}
