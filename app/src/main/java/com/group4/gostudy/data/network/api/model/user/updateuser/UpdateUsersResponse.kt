package com.group4.gostudy.data.network.api.model.user.updateuser

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UpdateUsersResponse(
    @SerializedName("data")
    val data: DataUpdateUser,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)
