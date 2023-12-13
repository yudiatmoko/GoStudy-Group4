package com.group4.gostudy.data.network.api.model.chapter


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class DataChapters(
    @SerializedName("chapters")
    val chapters: List<ChapterItemResponse>?
)