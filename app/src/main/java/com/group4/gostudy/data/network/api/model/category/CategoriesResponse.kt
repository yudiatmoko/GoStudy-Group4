package com.group4.gostudy.data.network.api.model.category

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CategoriesResponse(
    @SerializedName("data")
    val data: DataCategories?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)
