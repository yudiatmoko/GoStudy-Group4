package com.group4.gostudy.data.network.api.model.otp

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class OtpRequest(
    @SerializedName("otp")
    val otp: String?
)
