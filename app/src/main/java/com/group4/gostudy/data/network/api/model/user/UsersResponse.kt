package com.group4.gostudy.data.network.api.model.user

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.data.network.api.model.common.BaseResponse

@Keep
data class UsersResponse(
    val baseResponse: BaseResponse,
    @SerializedName("data")
    val data: DataUser
)
