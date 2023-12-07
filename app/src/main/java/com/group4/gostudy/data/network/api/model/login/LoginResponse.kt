package com.group4.gostudy.data.network.api.model.login

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.data.network.api.model.common.BaseResponse

@Keep
data class LoginResponse(
    val baseResponse: BaseResponse,
    @SerializedName("data")
    val data: DataLoginResponse?
)
