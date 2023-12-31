package com.group4.gostudy.data.network.api.model.moduleVideo

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.data.network.api.model.coursev2.ModuleItemResponseV2
@Keep
data class Module(
    @SerializedName("findModule")
    val findModule: ModuleItemResponseV2
)
