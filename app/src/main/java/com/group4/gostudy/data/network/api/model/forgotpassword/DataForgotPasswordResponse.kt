package com.group4.gostudy.data.network.api.model.forgotpassword

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DataForgotPasswordResponse(
    @SerializedName("token")
    val token: String?
)
