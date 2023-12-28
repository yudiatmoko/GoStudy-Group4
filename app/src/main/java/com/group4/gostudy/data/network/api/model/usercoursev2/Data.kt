package com.group4.gostudy.data.network.api.model.usercoursev2

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Data(
    @SerializedName("course")
    val course: List<UserCourseV2>?
)
