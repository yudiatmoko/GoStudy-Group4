package com.group4.gostudy.data.network.api.model.usercoursev2

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.model.Category

@Keep
data class CategoryUserCourse(
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

fun CategoryUserCourse.toCategory() = Category(
    id = this.id ?: 0,
    name = this.name.orEmpty(),
    slug = this.slug.orEmpty(),
    imageUrl = this.imageUrl.orEmpty(),
    imageId = this.imageId.orEmpty(),
    createdAt = this.createdAt.orEmpty(),
    updatedAt = this.updatedAt.orEmpty()
)

fun Collection<CategoryUserCourse>.toCategoryList() = this.map {
    it.toCategory()
}
