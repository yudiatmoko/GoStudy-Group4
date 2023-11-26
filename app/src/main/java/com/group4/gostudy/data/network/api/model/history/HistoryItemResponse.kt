package com.group4.gostudy.data.network.api.model.history

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.model.History

@Keep
data class HistoryItemResponse(
    @SerializedName("category")
    val category: String?,
    @SerializedName("duration")
    val duration: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("instructor")
    val instructor: String?,
    @SerializedName("level")
    val level: String?,
    @SerializedName("modules")
    val modules: String?,
    @SerializedName("paidStatus")
    val paidStatus: Boolean?,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("title")
    val title: String?
)

fun HistoryItemResponse.toHistory() = History(
    id = this.id ?: 0,
    category = this.category.orEmpty(),
    duration = this.duration.orEmpty(),
    img = this.image.orEmpty(),
    instructor = this.instructor.orEmpty(),
    level = this.level.orEmpty(),
    title = this.title.orEmpty(),
    modules = this.modules.orEmpty(),
    rating = this.rating.orEmpty(),
    paidStatus = this.paidStatus ?: true
)

fun Collection<HistoryItemResponse>.toHistoryList() = this.map {
    it.toHistory()
}
