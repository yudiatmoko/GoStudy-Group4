package com.group4.gostudy.data.network.api.model.user.updateuser

import com.google.errorprone.annotations.Keep
import retrofit2.http.Field

@Keep
data class UpdateUserRequest(
    @Field("name")
    val name: String,
    @Field("phoneNumber")
    val phoneNumber: String,
    @Field("country")
    val country: String,
    @Field("city")
    val city: String,
    @Field("image")
    val image: String
)
