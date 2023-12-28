package com.group4.gostudy.data.network.api.model.notification

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NotificationsResponse(
    @SerializedName("data")
    val data: DataNotificationsResponse?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)
