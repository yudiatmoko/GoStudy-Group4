package com.group4.gostudy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/
@Parcelize
data class PopularCourse(
    val benefits: String?,
    val category: Category?,
    val categoryId: Int?,
    val classCode: String?,
    val courseBy: String?,
    val createdAt: String?,
    val createdBy: Int?,
    val description: String?,
    val id: Int?,
    val rating: Double,
    val imageId: String?,
    val imageUrl: String?,
    val level: String?,
    val name: String?,
    val price: Int?,
    val totalDuration: Int?,
    val totalModule: Int?,
    val type: String?,
    val updatedAt: String?
) : Parcelable
