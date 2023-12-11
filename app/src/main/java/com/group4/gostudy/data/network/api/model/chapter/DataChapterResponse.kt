package com.group4.gostudy.data.network.api.model.chapter

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.model.Chapter

@Keep
data class DataChapterResponse(
    @SerializedName("noChapter")
    val noChapter: String?,
    @SerializedName("courseId")
    var courseId: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("data")
    val data: List<String>?,
    @SerializedName("time")
    val time: String?

)

fun DataChapterResponse.toChapter() = Chapter(
    noChapter = this.noChapter.orEmpty(),
    courseId = this.courseId ?: 0,
    name = this.name.orEmpty(),
    createdAt = this.createdAt.orEmpty(),
    updateAt = this.updatedAt.orEmpty(),
    data = this.data.orEmpty(),
    time = this.time.orEmpty()

)

fun Collection<DataChapterResponse>.toChapterList() = this.map {
    it.toChapter()
}
