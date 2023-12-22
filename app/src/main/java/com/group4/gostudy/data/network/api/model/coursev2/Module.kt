package com.group4.gostudy.data.network.api.model.coursev2


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Module(
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
    val isUnlocked: Any?,
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