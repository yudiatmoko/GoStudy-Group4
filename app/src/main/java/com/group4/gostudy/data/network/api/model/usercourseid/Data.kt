package com.group4.gostudy.data.network.api.model.usercourseid

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Data(
    @SerializedName("course")
    val course: UserCourseByIdResponse?
)
