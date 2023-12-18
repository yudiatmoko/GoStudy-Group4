package com.group4.gostudy.data.network.api.model.chapter

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.model.Chapter

@Keep
data class ChapterItemRespone(
    @SerializedName("courseId")
    val courseId: Int?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("noChapter")
    val noChapter: Int?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)

fun ChapterItemRespone.toChapter() = Chapter(
    courseId = this.courseId ?: 0,
    name = this.name.orEmpty(),
    noChapter = this.noChapter ?: 0,
    updateAt = this.updatedAt.orEmpty(),
    createAt = this.createdAt.orEmpty()

)

fun Collection<ChapterItemRespone>.toChapterList() = this.map {
    it.toChapter()
}
