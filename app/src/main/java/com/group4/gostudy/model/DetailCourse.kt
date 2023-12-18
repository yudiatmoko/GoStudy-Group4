package com.group4.gostudy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailCourse(
    val benefits: String?,
    val category: Category?,
    val chapter: Chapter?,
    val module: Module?,
    val categoryId: Int?,
    val classCode: String?,
    val courseBy: String?,
    val createdAt: String?,
    val createdBy: Int?,
    val description: String?,
    val id: Int?,
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
