package com.group4.gostudy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val createdAt: String?,
    val id: Int?,
    val imageId: String?,
    val imageUrl: String?,
    val name: String?,
    val slug: String?,
    val updatedAt: String?,
    var isChecked: Boolean = false
) : Parcelable
