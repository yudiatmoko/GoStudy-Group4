package com.group4.gostudy.data.network.api.model.notification

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.model.Notification

@Keep
data class NotificationItemResponse(
    @SerializedName("category")
    val category: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)

fun NotificationItemResponse.toNotification() = Notification(
    id = this.id ?: 0,
    title = this.title.orEmpty(),
    description = this.description.orEmpty(),
    category = this.category.orEmpty(),
    createdAt = this.createdAt.orEmpty(),
    updatedAt = this.updatedAt.orEmpty()
)

fun Collection<NotificationItemResponse>.toNotificationList() = this.map {
    it.toNotification()
}
