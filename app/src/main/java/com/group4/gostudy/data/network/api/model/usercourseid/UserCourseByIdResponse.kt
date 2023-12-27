package com.group4.gostudy.data.network.api.model.usercourseid

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.model.Category
import com.group4.gostudy.model.Course

@Keep
data class UserCourseByIdResponse(
    @SerializedName("benefits")
    val benefits: List<String>?,
    @SerializedName("categoryId")
    val categoryId: Int?,
    @SerializedName("Chapters")
    val chapters: List<UserChapter>?,
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
    val totalDuration: Int?,
    @SerializedName("totalModule")
    val totalModule: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    val category: Category?
)

fun UserCourseByIdResponse.toUserCourse() = Course(
    benefits = this.benefits ?: emptyList(),
    categoryId = this.categoryId ?: 0,
    classCode = this.classCode.orEmpty(),
    courseBy = this.courseBy.orEmpty(),
    rating = this.rating ?: 0.0,
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
    category = null,
    promoPercentage = this.promoPercentage ?: 0,
    chapters = this.chapters?.toChapterList()
)

fun Collection<UserCourseByIdResponse>.toCourseList() = this.map {
    it.toUserCourse()
}
