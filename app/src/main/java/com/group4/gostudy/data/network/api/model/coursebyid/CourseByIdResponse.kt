package com.group4.gostudy.data.network.api.model.coursebyid

data class CourseByIdResponse(
    val data: DataCourseById,
    val message: String,
    val status: String
)