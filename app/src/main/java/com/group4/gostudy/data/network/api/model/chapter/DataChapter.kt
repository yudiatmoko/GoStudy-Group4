package com.group4.gostudy.data.network.api.model.chapter

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DataChapter(
    @SerializedName("chapter")
    val chapters: List<DataChapterResponse>?
)
