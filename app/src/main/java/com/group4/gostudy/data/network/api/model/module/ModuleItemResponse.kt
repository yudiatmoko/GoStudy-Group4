package com.group4.gostudy.data.network.api.model.module

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.model.Module

@Keep
data class ModuleItemResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("no")
    val no: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("chapterId")
    val chapterId: Int?,
    @SerializedName("videoUrl")
    val videoUrl: String?,
    @SerializedName("videoId")
    val videoId: String?,
    @SerializedName("duration")
    val duration: Int?,
    @SerializedName("createdBy")
    val createdBy: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("updateAt")
    val updatedAt: String?,

    )

fun ModuleItemResponse.toModule() = Module(
    id = this.id ?: 0,
    no = this.no ?: 0,
    name = this.name.orEmpty(),
    description = this.description.orEmpty(),
    chapterId = this.chapterId ?: 0,
    videoUrl = this.videoUrl.orEmpty(),
    videoId = this.videoId.orEmpty(),
    duration = this.duration ?: 0,
    createdBy = this.createdBy.orEmpty(),
    createdAt = this.createdAt.orEmpty(),
    updatedAt = this.updatedAt.orEmpty(),
)

fun Collection<ModuleItemResponse>.toModuleList() = this.map {
    it.toModule()
}