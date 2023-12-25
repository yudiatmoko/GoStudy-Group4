package com.group4.gostudy.data.network.api.model.payment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PaymentRequest(
    @SerializedName("courseId")
    val courseId: Int?
)
