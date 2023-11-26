package com.group4.gostudy.data.network.api.model.history

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class HistoriesResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("data")
    val data: List<HistoryItemResponse>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)
