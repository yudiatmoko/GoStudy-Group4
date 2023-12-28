package com.group4.gostudy.data.network.api.model.notification

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DataNotificationsResponse(
    @SerializedName("allNotif")
    val allNotif: List<AllNotifItemResponse>?
)
