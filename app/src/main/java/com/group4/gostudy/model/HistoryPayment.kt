package com.group4.gostudy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryPayment(
    val course: Course?,
    val createdAt: String?,
    val orderId: String?,
    val price: Int?,
    val status: String?
) : Parcelable
