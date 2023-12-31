package com.group4.gostudy.data.network.api.model.payment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.model.Payment

@Keep
data class PaymentItemResponse(
    @SerializedName("courseId")
    val courseId: Int?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("orderId")
    val orderId: String?,
    @SerializedName("paymentType")
    val paymentType: String?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("redirect_url")
    val redirect_url: String?,
    @SerializedName("settelemtTime")
    val settlementTime: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("token")
    val token: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("userId")
    val userId: Int?
)
fun PaymentItemResponse.toPayment() = Payment(
    courseId = this.courseId ?: 0,
    createdAt = this.createdAt.orEmpty(),
    id = this.id ?: 0,
    orderId = this.orderId.orEmpty(),
    paymentType = this.paymentType.orEmpty(),
    price = this.price ?: 0,
    redirectUrl = this.redirect_url.orEmpty(),
    settlementTime = this.settlementTime.orEmpty(),
    status = this.status.orEmpty(),
    token = this.token.orEmpty(),
    updatedAt = this.updatedAt.orEmpty(),
    userId = this.userId ?: 0
)
