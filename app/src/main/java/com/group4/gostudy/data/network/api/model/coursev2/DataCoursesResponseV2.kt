package com.group4.gostudy.data.network.api.model.coursev2


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class DataCoursesResponseV2(
    @SerializedName("courses")
    val courses: List<CourseItemResponseV2>?
)