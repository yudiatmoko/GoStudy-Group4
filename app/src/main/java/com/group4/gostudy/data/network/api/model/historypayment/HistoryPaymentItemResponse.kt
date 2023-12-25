package com.group4.gostudy.data.network.api.model.historypayment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.data.network.api.model.coursev2.CourseItemResponseV2
import com.group4.gostudy.data.network.api.model.coursev2.toCourse
import com.group4.gostudy.model.HistoryPayment

@Keep
data class HistoryPaymentItemResponse(
    @SerializedName("course")
    val course: CourseItemResponseV2,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("orderId")
    val orderId: String?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("status")
    val status: String?
)

fun HistoryPaymentItemResponse.toHistoryPayment() = HistoryPayment(
    price = this.price ?: 0,
    orderId = this.orderId.orEmpty(),
    status = this.status.orEmpty(),
    course = this.course.toCourse(),
    createdAt = this.createdAt.orEmpty()
)

fun Collection<HistoryPaymentItemResponse>.toHistoryPaymentList() = this.map {
    it.toHistoryPayment()
}
