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
    val createdBy: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("duration")
    val duration: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("isUnlocked")
    val isUnlocked: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("noModule")
    val noModule: Int?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("videoId")
    val videoId: String?,
    @SerializedName("videoUrl")
    val videoUrl: String?
)

fun ModuleItemResponse.toModule() = Module(
    id = this.id ?: 0,
    noModule = this.noModule ?: 0,
    chapterId = this.chapterId ?: 0,
    name = this.name.orEmpty(),
    createdAt = this.createdAt.orEmpty(),
    updatedAt = this.updatedAt.orEmpty(),
    createdBy = this.createdBy ?: 0,
    description = this.description.orEmpty(),
    videoId = this.videoId.orEmpty(),
    videoUrl = this.videoUrl.orEmpty(),
    duration = this.duration ?: 0,
    isUnlocked = this.isUnlocked
)

fun Collection<ModuleItemResponse>.toModuleList() = this.map {
    it.toModule()
}
