package com.group4.gostudy.data.network.api.model.payment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.data.network.api.model.common.BaseResponse

@Keep
data class PaymentResponse(
    @SerializedName("data")
    val data: DataPaymentResponse,
    val baseResponse: BaseResponse

)
