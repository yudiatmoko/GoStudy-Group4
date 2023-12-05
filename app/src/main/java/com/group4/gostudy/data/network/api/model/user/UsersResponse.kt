package com.group4.gostudy.data.network.api.model.user

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UsersResponse(
    @SerializedName("data")
    val data: DataUser,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)
