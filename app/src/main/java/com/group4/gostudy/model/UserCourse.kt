package com.group4.gostudy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserCourse(
    val totalProgress: Int?,
    val courseX: Course?,
    val courseId: Int?,
    val createdAt: String?,
    val id: Int?,
    val isAccessible: Boolean?,
    val updatedAt: String?,
    val userId: Int?
) : Parcelable
