package com.group4.gostudy.data.network.api.model.forgotpassword

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ForgotPasswordResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)
