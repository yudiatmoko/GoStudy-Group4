package com.group4.gostudy.data.network.api.model.modulevideo

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class DataModuleVideo(
    @SerializedName("module")
    val module: Module
)
