package com.group4.gostudy.model

data class HistoryPayment(
    val course: Course?,
    val createdAt: String?,
    val orderId: String?,
    val price: Int?,
    val status: String?
)
