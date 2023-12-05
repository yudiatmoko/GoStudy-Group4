package com.group4.gostudy.data.network.api.model.user.updateuser

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.data.network.api.model.user.DataUserResponse

@Keep
data class DataUpdateUser(
    @SerializedName("updatedUser")
    val updatedUser: DataUserResponse
)
