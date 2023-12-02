package com.group4.gostudy.data.network.api.model.user.logout

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

@Keep
data class LogoutResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("success")
    val success: Boolean?
)
