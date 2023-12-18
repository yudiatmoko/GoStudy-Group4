package com.group4.gostudy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Module(
    val chapterId: Int?,
    val createdAt: String?,
    val createdBy: Int?,
    val description: String?,
    val duration: Int?,
    val id: Int?,
    val name: String?,
    val no: Int?,
    val updatedAt: String?,
    val videoId: String?,
    val videoUrl: String?
) : Parcelable
