package com.group4.gostudy.data.network.api.model.login

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LoginResponse(
    @SerializedName("data")
    val data: DataLoginResponse?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)
