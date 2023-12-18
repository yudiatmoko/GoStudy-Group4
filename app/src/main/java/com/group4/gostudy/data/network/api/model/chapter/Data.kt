package com.group4.gostudy.data.network.api.model.chapter

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Data(
    @SerializedName("chapters")
    val chapters: List<ChapterItemRespone>?
)
