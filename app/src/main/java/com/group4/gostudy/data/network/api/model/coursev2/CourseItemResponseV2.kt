package com.group4.gostudy.data.network.api.model.coursev2


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class CourseItemResponseV2(
    @SerializedName("benefits")
    val benefits: List<String>?,
    @SerializedName("Category")
    val category: Category?,
    @SerializedName("categoryId")
    val categoryId: Int?,
    @SerializedName("Chapters")
    val chapters: List<Chapter>?,
    @SerializedName("classCode")
    val classCode: String?,
    @SerializedName("courseBy")
    val courseBy: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("createdBy")
    val createdBy: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("imageId")
    val imageId: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("level")
    val level: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("promoPercentage")
    val promoPercentage: Int?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("totalDuration")
    val totalDuration: Any?,
    @SerializedName("totalModule")
    val totalModule: Any?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)