package com.group4.gostudy.data.network.api.model.usercoursev2

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.group4.gostudy.data.network.api.model.coursev2.CourseItemResponseV2
import com.group4.gostudy.data.network.api.model.coursev2.toCourse
import com.group4.gostudy.model.UserCourse

@Keep
data class UserCourseV2(
    @SerializedName("Course")
    val coursex: CourseItemResponseV2?,
    @SerializedName("courseId")
    val courseId: Int?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("isAccessible")
    val isAccessible: Boolean?,
    @SerializedName("totalProgress")
    val totalProgress: Int?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("userId")
    val userId: Int?
)

fun UserCourseV2.toUserCourse() = UserCourse(
    totalProgress = this.totalProgress ?: 0,
    courseX = this.coursex?.toCourse(),
    courseId = this.courseId ?: 0,
    createdAt = this.createdAt.orEmpty(),
    id = this.id ?: 0,
    isAccessible = this.isAccessible ?: false,
    updatedAt = this.updatedAt.orEmpty(),
    userId = this.userId ?: 0
)
fun Collection<UserCourseV2>.toCourseList() = this.map {
    it.toUserCourse()
}
