package com.group4.gostudy.data.network.api.model.profile

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.model.Profile

@Keep
data class ProfileDataResponse(
    @SerializedName("city")
    val city: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("password")
    val password: String?
)

fun ProfileDataResponse.toProfile() = Profile(
    name = this.name.orEmpty(),
    email = this.email.orEmpty(),
    phone = this.phone.orEmpty(),
    country = this.country.orEmpty(),
    city = this.city.orEmpty(),
    password = this.password.orEmpty()
)

fun Collection<ProfileDataResponse>.toProfileView() = this.map {
    it.toProfile()
}
