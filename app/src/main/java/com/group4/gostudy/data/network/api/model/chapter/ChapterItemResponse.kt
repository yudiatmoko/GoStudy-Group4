package com.group4.gostudy.data.network.api.model.chapter


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.group4.gostudy.data.network.api.model.category.CategoryItemResponse
import com.group4.gostudy.model.Category
import com.group4.gostudy.model.Chapter

@Keep
data class ChapterItemResponse(
    @SerializedName("courseId")
    val courseId: Int?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("noChapter")
    val noChapter: Int?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)

fun ChapterItemResponse.toChapter() = Chapter(
    id = this.id ?: 0,
    courseId = this.courseId ?: 0,
    noChapter = this.noChapter ?: 0,
    name = this.name.orEmpty(),
    createdAt = this.createdAt.orEmpty(),
    updatedAt = this.updatedAt.orEmpty(),
)

fun Collection<ChapterItemResponse>.toChapterList() = this.map {
    it.toChapter()
}