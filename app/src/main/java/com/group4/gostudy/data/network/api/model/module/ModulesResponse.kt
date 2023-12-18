package com.group4.gostudy.data.network.api.model.module

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.data.network.api.model.common.BaseResponse

@Keep
data class ModulesResponse(
    val baseResponse: BaseResponse,
    @SerializedName("data")
    val data: DataModules
)
