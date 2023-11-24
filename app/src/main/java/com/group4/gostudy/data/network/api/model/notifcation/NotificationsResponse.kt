package com.group4.gostudy.data.network.api.model.notifcation

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

@Keep
data class NotificationsResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("data")
    val data: List<NotificationItemResponse>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)
