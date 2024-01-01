package com.group4.gostudy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Payment(
    val courseId: Int,
    val createdAt: String,
    val id: Int,
    val orderId: String,
    val paymentType: String,
    val price: Int,
    val redirectUrl: String,
    val settlementTime: String,
    val status: String,
    val token: String,
    val updatedAt: String,
    val userId: Int
) : Parcelable
