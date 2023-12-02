package com.group4.gostudy.data.network.api.model.user.updatepassword

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UpdatePasswordRequest(
    @SerializedName("confirmPassword")
    val confirmPassword: String?,
    @SerializedName("newPassword")
    val newPassword: String?,
    @SerializedName("oldPassword")
    val oldPassword: String?
)
