package com.group4.gostudy.model

import com.group4.gostudy.data.network.api.model.notification.NotificationItemResponse

data class AllNotif(
    val createdAt: String?,
    val id: Int?,
    val isRead: Boolean?,
    val notifId: Int?,
    val notification: NotificationItemResponse?,
    val updatedAt: String?,
    val userId: Int?
)
