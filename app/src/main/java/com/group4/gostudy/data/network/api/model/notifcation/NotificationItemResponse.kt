package com.group4.gostudy.data.network.api.model.notifcation

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.model.Notification

@Keep
data class NotificationItemResponse(
    @SerializedName("content")
    val content: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("subtitle")
    val subtitle: String?,
    @SerializedName("title")
    val title: String?
)

fun NotificationItemResponse.toNotification() = Notification(
    title = this.title.orEmpty(),
    subtitle = this.subtitle.orEmpty(),
    id = this.id ?: 0,
    content = this.content.orEmpty()
)

fun Collection<NotificationItemResponse>.toNotificationList() = this.map {
    it.toNotification()
}
