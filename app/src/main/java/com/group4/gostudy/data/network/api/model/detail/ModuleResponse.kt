package com.group4.gostudy.data.network.api.model.detail

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.data.network.api.model.common.BaseResponse

@Keep
data class ModuleResponse(
    @SerializedName("data")
    val dataModules: DataModules?,
    val baseResponse: BaseResponse
)
