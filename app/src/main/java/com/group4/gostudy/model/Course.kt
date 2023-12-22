package com.group4.gostudy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Course(
    val benefits: List<String>?,
    val category: Category?,
    val categoryId: Int?,
    val chapters: List<Chapter>?,
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
    val promoPercentage: Int?,
    val rating: Double?,
    val totalDuration: Int?,
    val totalModule: Int?,
    val type: String?,
    val updatedAt: String?
) : Parcelable
