package com.group4.gostudy.data.network.api.model.coursebyid

import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CourseByIdResponse(
    @SerializedName("data")
    val data: DataCourseById,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)
