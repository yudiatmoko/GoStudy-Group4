package com.group4.gostudy.model

data class SectionedData(
    val dataHeader: List<Chapter>?,
    val dataItem: List<Module>?
)

data class Chapter(
    val noChapter: Int?,
    var id: Int?,
    val courseId: Int?,
    val name: String?,
    val createdAt: String?,
    val updateAt: String?
)
data class Module(
    val id:Int,
    val no:Int,
    val name:String,
    val description: String,
    val chapterId:Int?,
    val videoUrl:String,
    val videoId: String,
    val duration: Int,
    val createdBy: String,
    val createdAt: String,
    val updatedAt: String,
)
