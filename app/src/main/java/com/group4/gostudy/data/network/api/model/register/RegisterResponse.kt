package com.group4.gostudy.data.network.api.model.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?
)
