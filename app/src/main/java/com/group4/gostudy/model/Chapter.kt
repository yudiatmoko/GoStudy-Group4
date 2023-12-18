package com.group4.gostudy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chapter(
    val courseId: Int?,
    val createAt: String?,
    val name: String?,
    val noChapter: Int?,
    val updateAt: String?

) : Parcelable
