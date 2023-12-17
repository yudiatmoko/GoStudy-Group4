package com.group4.gostudy.data.network.api.model.module

import com.google.gson.annotations.SerializedName

data class DataModules(
    @SerializedName("modules")
    val modules: List<ModuleItemResponse>
)
