package com.group4.gostudy.data.network.api.model.coursev2


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Category(
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("imageId")
    val imageId: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)