package com.group4.gostudy.data.network.api.model.forgotpassword

import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ForgotPasswordRequest(
    @SerializedName("email")
    val email: String?
)
