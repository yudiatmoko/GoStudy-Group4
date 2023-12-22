package com.group4.gostudy.data.network.api.model.coursev2


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class CoursesResponseV2(
    @SerializedName("data")
    val data: DataCoursesResponseV2?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)