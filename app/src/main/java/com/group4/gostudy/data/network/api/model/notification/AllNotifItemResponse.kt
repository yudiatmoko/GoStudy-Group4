package com.group4.gostudy.data.network.api.model.notification

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.model.AllNotif

@Keep
data class AllNotifItemResponse(
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("isRead")
    val isRead: Boolean?,
    @SerializedName("notifId")
    val notifId: Int?,
    @SerializedName("Notification")
    val notification: NotificationItemResponse?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("userId")
    val userId: Int?
)

fun AllNotifItemResponse.toAllNotif() = AllNotif(
    id = this.id ?: 0,
    userId = this.userId ?: 0,
    notifId = this.notifId ?: 0,
    isRead = this.isRead,
    notification = this.notification,
    createdAt = this.createdAt.orEmpty(),
    updatedAt = this.updatedAt.orEmpty()
)

fun Collection<AllNotifItemResponse>.toAllNotifList() = this.map {
    it.toAllNotif()
}
