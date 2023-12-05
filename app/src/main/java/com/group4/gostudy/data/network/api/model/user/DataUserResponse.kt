package com.group4.gostudy.data.network.api.model.user

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.model.User

@Keep
data class DataUserResponse(
    @SerializedName("city")
    val city: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("imageId")
    val imageId: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("phoneNumber")
    val phoneNumber: String?,
    @SerializedName("role")
    val role: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("verify")
    val verify: Boolean?
)

fun DataUserResponse.toUser() = User(
    name = this.name.orEmpty(),
    email = this.email.orEmpty(),
    phoneNumber = this.phoneNumber.orEmpty(),
    country = this.country.orEmpty(),
    city = this.city.orEmpty(),
    imageUrl = this.imageUrl.orEmpty(),
    imageId = this.imageId.orEmpty(),
    role = this.role.orEmpty(),
    createdAt = this.createdAt.orEmpty(),
    id = this.id ?: 0,
    verify = this.verify ?: false,
    updatedAt = this.updatedAt.orEmpty(),
    password = this.password.orEmpty()
)
