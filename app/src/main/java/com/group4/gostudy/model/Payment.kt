package com.group4.gostudy.model

data class Payment(
    val courseId: Int,
    val createdAt: String,
    val id: Int,
    val orderId: String,
    val paymentType: Any,
    val price: Int,
    val redirectUrl: String,
    val settlementTime: Any,
    val status: String,
    val token: String,
    val updatedAt: String,
    val userId: Int
)
