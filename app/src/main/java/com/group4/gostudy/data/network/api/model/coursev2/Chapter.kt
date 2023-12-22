package com.group4.gostudy.data.network.api.model.coursev2


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Chapter(
    @SerializedName("courseId")
    val courseId: Int?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("Modules")
    val modules: List<Module?>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("noChapter")
    val noChapter: Int?,
    @SerializedName("totalDuration")
    val totalDuration: Any?,
    @SerializedName("totalModule")
    val totalModule: Any?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)