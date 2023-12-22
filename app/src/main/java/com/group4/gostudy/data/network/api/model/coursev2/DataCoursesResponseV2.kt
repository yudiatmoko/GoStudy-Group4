package com.group4.gostudy.data.network.api.model.coursev2

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DataCoursesResponseV2(
    @SerializedName("courses")
    val courses: List<CourseItemResponseV2>?
)
