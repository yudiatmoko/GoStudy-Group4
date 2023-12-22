package com.group4.gostudy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chapter(
    val courseId: Int?,
    val createdAt: String?,
    val id: Int?,
    val modules: List<Module>?,
    val name: String?,
    val noChapter: Int?,
    val totalDuration: Int?,
    val totalModule: Int?,
    val updatedAt: String?
) : Parcelable
