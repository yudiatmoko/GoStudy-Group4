package com.group4.gostudy.data.network.api.model.historypayment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class HistoryPaymentsResponse(
    @SerializedName("data")
    val data: DataHistoryPayments?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)
