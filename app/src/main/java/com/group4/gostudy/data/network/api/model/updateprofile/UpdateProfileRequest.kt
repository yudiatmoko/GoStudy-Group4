package com.group4.gostudy.data.network.api.model.updateprofile

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UpdateProfileRequest(
    @SerializedName("city")
    val city: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?
)
