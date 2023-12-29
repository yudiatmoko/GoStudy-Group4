package com.group4.gostudy.data.network.api.model.coursev2

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.data.network.api.model.common.BaseResponse

@Keep
data class CoursesResponseV2(
    @SerializedName("data")
    val data: DataCoursesResponseV2?,
    val baseResponse: BaseResponse
)
