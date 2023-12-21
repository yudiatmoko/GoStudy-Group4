 
package com.group4.gostudy.data.network.api.model.course

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DataCourses(
    @SerializedName("courses")
    val courses: List<CourseItemResponse>?
)
