package com.group4.gostudy.data.network.api.model.module

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.model.Module

@Keep
data class ModuleItemResponse(
    @SerializedName("chapterId")
    val chapterId: Int?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("createdBy")
    val createdBy: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("duration")
    val duration: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("no")
    val no: Int?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("videoId")
    val videoId: String?,
    @SerializedName("videoUrl")
    val videoUrl: String?
)

fun ModuleItemResponse.toModule() = Module(
    chapterId = this.chapterId ?: 0,
    createdAt = this.createdAt.orEmpty(),
    createdBy = this.createdBy.orEmpty(),
    description = this.description.orEmpty(),
    duration = this.duration ?: 0,
    id = this.id ?: 0,
    name = this.name.orEmpty(),
    no = this.no ?: 0,
    updatedAt = this.updatedAt.orEmpty(),
    videoUrl = this.videoUrl.orEmpty(),
    videoId = this.videoId.orEmpty()
)

fun Collection<ModuleItemResponse>.toModuleList() = this.map {
    it.toModule()
}
