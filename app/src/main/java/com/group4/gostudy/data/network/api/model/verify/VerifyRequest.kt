package com.group4.gostudy.data.network.api.model.verify

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class VerifyRequest(
    @SerializedName("otp")
    val otp: String?
)
