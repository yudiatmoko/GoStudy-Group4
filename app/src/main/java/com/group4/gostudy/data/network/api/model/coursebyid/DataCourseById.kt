package com.group4.gostudy.data.network.api.model.coursebyid

import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.data.network.api.model.coursev2.CourseItemResponseV2
@Keep
data class DataCourseById(
    @SerializedName("course")
    val course: CourseItemResponseV2
)
