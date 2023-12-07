package com.group4.gostudy.data.network.api.model.common

import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

@Keep
data class BaseResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)
