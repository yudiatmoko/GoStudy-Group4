 
package com.group4.gostudy.data.network.api.model.course

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CoursesResponse(
    @SerializedName("data")
    val data: DataCourses?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)
