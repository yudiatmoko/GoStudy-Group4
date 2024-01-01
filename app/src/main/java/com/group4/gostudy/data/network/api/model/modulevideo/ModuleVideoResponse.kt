package com.group4.gostudy.data.network.api.model.modulevideo

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ModuleVideoResponse(
    val data: DataModuleVideo,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)
