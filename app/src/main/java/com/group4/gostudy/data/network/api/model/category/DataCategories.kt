package com.group4.gostudy.data.network.api.model.category

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DataCategories(
    @SerializedName("categories")
    val categories: List<CategoryItemResponse>?
)
