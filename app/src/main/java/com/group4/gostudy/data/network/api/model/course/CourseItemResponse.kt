package com.group4.gostudy.data.network.api.model.course

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.data.network.api.model.category.CategoryItemResponse
import com.group4.gostudy.data.network.api.model.category.toCategory
import com.group4.gostudy.model.PopularCourse

@Keep
data class CourseItemResponse(
    @SerializedName("benefits")
    val benefits: String?,
    @SerializedName("Category")
    val category: CategoryItemResponse?,
    @SerializedName("categoryId")
    val categoryId: Int?,
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
    @SerializedName("totalDuration")
    val totalDuration: Int?,
    @SerializedName("totalModule")
    val totalModule: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)

fun CourseItemResponse.toCourse() = PopularCourse(
    benefits = this.benefits.orEmpty(),
    categoryId = this.categoryId ?: 0,
    classCode = this.classCode.orEmpty(),
    courseBy = this.courseBy.orEmpty(),
    createdAt = this.createdAt.orEmpty(),
    createdBy = this.createdBy ?: 0,
    description = this.description.orEmpty(),
    id = this.id ?: 0,
    imageId = this.imageId.orEmpty(),
    imageUrl = this.imageUrl.orEmpty(),
    level = this.level.orEmpty(),
    name = this.name.orEmpty(),
    price = this.price ?: 0,
    totalDuration = this.totalDuration ?: 0,
    totalModule = this.totalModule ?: 0,
    type = this.type.orEmpty(),
    updatedAt = this.updatedAt.orEmpty(),
    category = this.category?.toCategory()
)

fun Collection<CourseItemResponse>.toCourseList() = this.map {
    it.toCourse()
}
