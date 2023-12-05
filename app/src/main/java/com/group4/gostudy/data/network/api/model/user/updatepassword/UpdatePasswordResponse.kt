package com.group4.gostudy.data.network.api.model.user.updatepassword

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UpdatePasswordResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)
