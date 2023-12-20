package com.group4.gostudy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Module(
    val id: Int,
    val no: Int,
    val name: String,
    val description: String,
    val chapterId: Int?,
    val videoUrl: String,
    val videoId: String,
    val duration: Int,
    val createdBy: String,
    val createdAt: String,
    val updatedAt: String
) : Parcelable
