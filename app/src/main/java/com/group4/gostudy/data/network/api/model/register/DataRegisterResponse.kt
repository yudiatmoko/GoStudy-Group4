package com.group4.gostudy.data.network.api.model.register

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.data.network.api.model.user.DataUserResponse

@Keep
data class DataRegisterResponse(
    @SerializedName("token")
    val token: String?,
    @SerializedName("user")
    val user: DataUserResponse?
)
