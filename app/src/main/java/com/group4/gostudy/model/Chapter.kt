package com.group4.gostudy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chapter(
    val noChapter: Int?,
    var id: Int?,
    val courseId: Int?,
    val name: String?,
    val createdAt: String?,
    val updateAt: String?
) : Parcelable
