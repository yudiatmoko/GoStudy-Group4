package com.group4.gostudy.data.network.api.model.register

import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RegisterRequest(
    @SerializedName("name")
    val name: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("phoneNumber")
    val phoneNumber: String?
)
