package com.group4.gostudy.data.network.api.model.register

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.data.network.api.model.common.BaseResponse

@Keep
data class RegistersResponse(
    @SerializedName("data")
    val data: DataRegisterResponse?,
    val baseResponse: BaseResponse
)
