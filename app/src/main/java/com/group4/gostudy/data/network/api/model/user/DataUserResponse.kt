package com.group4.gostudy.data.network.api.model.user

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.model.User

@Keep
data class DataUserResponse(
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("_id")
    val id: String?,
    @SerializedName("image_profile")
    val imageProfile: String?,
    @SerializedName("isActive")
    val isActive: Boolean?,
    @SerializedName("isVerify")
    val isVerify: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("role")
    val role: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("city")
    val city: String?
)

fun DataUserResponse.toUser() = User(
    name = this.name.orEmpty(),
    email = this.email.orEmpty(),
    phone = this.phone.orEmpty(),
    country = this.country.orEmpty(),
    city = this.city.orEmpty(),
    username = this.username.orEmpty(),
    imageProfile = this.imageProfile.orEmpty(),
    role = this.role.orEmpty(),
    createdAt = this.createdAt.orEmpty(),
    id = this.id.orEmpty(),
    isActive = this.isActive ?: false,
    isVerify = this.isVerify ?: false
)
