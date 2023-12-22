package com.group4.gostudy.data.network.api.model.chapter

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.model.Chapter

@Keep
data class DataChapterResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("noChapter")
    val noChapter: Int?,
    @SerializedName("courseId")
    var courseId: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("totalDuration")
    val totalDuration: Int?,
    @SerializedName("totalModule")
    val totalModule: Int?
)

fun DataChapterResponse.toChapter() = Chapter(
    id = this.id ?: 0,
    noChapter = this.noChapter ?: 0,
    courseId = this.courseId ?: 0,
    name = this.name.orEmpty(),
    createdAt = this.createdAt.orEmpty(),
    updateAt = this.updatedAt.orEmpty(),
    totalDuration = this.totalDuration ?: 0,
    totalModule = this.totalModule ?: 0
)

fun Collection<DataChapterResponse>.toChapterList() = this.map {
    it.toChapter()
}
