package com.group4.gostudy.data.network.api.model.historypayment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DataHistoryPayments(
    @SerializedName("historyPayment")
    val historyPayment: List<HistoryPaymentItemResponse>?
)
