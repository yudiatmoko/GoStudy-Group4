package com.group4.gostudy.data.network.api.model.user

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DataUser(
    @SerializedName("user")
    val user: DataUserResponse
)
