package com.group4.gostudy.data.network.api.model.usercoursev2

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UserCourseResponseV2(
    @SerializedName("data")
    val data: Data?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)
