package com.group4.gostudy.data.network.api.model.chapter

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.data.network.api.model.common.BaseResponse

@Keep
data class ChapterRespone(
    @SerializedName("data")
    val data: Data?,
    val baseResponse: BaseResponse
)
