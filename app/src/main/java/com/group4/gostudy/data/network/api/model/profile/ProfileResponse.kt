package com.group4.gostudy.data.network.api.model.profile

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ProfileResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("data")
    val data: ProfileDataResponse,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)
