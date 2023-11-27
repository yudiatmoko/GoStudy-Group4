package com.group4.gostudy.data.network.api.model.updateprofile

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UpdateProfileResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)
