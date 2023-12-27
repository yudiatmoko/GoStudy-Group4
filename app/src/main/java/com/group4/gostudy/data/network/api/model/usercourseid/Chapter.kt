package com.group4.gostudy.data.network.api.model.usercourseid

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.data.network.api.model.coursev2.toModuleList
import com.group4.gostudy.model.Chapter

@Keep
data class UserChapter(
    @SerializedName("courseId")
    val courseId: Int?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("Modules")
    val modules: List<UserModule>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("noChapter")
    val noChapter: Int?,
    @SerializedName("totalDuration")
    val totalDuration: Int?,
    @SerializedName("totalModule")
    val totalModule: Int?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)

fun UserChapter.toChapter() = Chapter(
    id = this.id ?: 0,
    noChapter = this.noChapter ?: 0,
    courseId = this.courseId ?: 0,
    name = this.name.orEmpty(),
    createdAt = this.createdAt.orEmpty(),
    updatedAt = this.updatedAt.orEmpty(),
    totalDuration = this.totalDuration ?: 0,
    totalModule = this.totalModule ?: 0,
    modules = this.modules?.toModuleList()
)

fun Collection<UserChapter>.toChapterList() = this.map {
    it.toChapter()
}
