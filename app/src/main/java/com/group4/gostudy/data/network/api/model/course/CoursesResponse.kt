package com.group4.gostudy.data.network.api.model.course

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.data.network.api.model.common.BaseResponse

@Keep
data class CoursesResponse(
    @SerializedName("data")
    val data: DataCourses?,
    val baseResponse: BaseResponse
)
