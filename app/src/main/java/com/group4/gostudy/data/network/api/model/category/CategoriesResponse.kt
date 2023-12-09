package com.group4.gostudy.data.network.api.model.category

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.data.network.api.model.common.BaseResponse

@Keep
data class CategoriesResponse(
    @SerializedName("data")
    val data: DataCategories?,
    val baseResponse: BaseResponse
)
