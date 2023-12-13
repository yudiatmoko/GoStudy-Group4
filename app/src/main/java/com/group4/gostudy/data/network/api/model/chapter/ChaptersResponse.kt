package com.group4.gostudy.data.network.api.model.chapter


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ChaptersResponse(
    @SerializedName("data")
    val data: DataChapters?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)