package com.group4.gostudy.data.network.api.model.usercourseid

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.data.network.api.model.coursev2.CourseItemResponseV2

@Keep
data class Data(
    @SerializedName("course")
    val course: CourseItemResponseV2?
)
