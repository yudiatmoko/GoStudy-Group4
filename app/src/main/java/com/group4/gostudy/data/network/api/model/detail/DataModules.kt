package com.group4.gostudy.data.network.api.model.detail

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DataModules(
    @SerializedName("modules")
    val module: List<ModuleItemResponse>?
)
