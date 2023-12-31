package com.group4.gostudy.data.network.api.model.usercourseid

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UserCourseById(
    @SerializedName("data")
    val data: Data?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)
