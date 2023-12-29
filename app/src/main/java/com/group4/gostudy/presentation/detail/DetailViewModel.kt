package com.group4.gostudy.presentation.detail

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.group4.gostudy.model.Course

class DetailViewModel(
    private val extras: Bundle?
) : ViewModel() {
    val course = extras?.getParcelable<Course>(DetailCourseActivity.EXTRA_PRODUCT)

    var idCourse: Int? = course?.id

    // val idChapter : Int? = course?.
}
