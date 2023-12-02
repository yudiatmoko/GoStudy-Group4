package com.group4.gostudy.data.network.api.model.user.updateuser

import com.google.errorprone.annotations.Keep
import retrofit2.http.Field

@Keep
data class UpdateUserRequest(
    @Field("name")
    val name: String,
    @Field("email")
    val email: String,
    @Field("phone")
    val phone: String,
    @Field("country")
    val country: String,
    @Field("city")
    val city: String,
    @Field("imageProfile")
    val imageProfile: String
)
