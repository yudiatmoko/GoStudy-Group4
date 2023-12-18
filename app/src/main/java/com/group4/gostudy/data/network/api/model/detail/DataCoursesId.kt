package com.group4.gostudy.data.network.api.model.detail

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DataCoursesId(
    @SerializedName("course")
    val course: List<CourseIdItemResponse>?
)
