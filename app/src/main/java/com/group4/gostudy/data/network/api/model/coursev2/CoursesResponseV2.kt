package com.group4.gostudy.data.network.api.model.coursev2

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CoursesResponseV2(
    @SerializedName("data")
    val data: DataCoursesResponseV2?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)
