package com.group4.gostudy.model

import com.group4.gostudy.R

data class Course(
    val id: Int,
    val category: String,
    val img: Int,
    val title: String,
    val instructor: String,
    val level: String,
    val modules: String,
    val duration: String,
    val rating: String,
    val price: Double
)

object CourseProvider {
    fun getDummyData(): List<Course> {
        return listOf(
            Course(
                1,
                "UI/UX Design",
                R.drawable.img_ui_ux,
                "Belajar Web Design dengan Figma",
                "Angela Doe",
                "Intermediate Level",
                "12 Modul",
                "90 Menit",
                "4.9",
                350000.0
            ),
            Course(
                2,
                "Mobile Developer",
                R.drawable.img_ui_ux,
                "Android Engineering",
                "Hermas Yuda",
                "Beginner Level",
                "12 Modul",
                "120 Menit",
                "4.9",
                350000.0
            ),
            Course(
                3,
                "Mobile Developer",
                R.drawable.img_ui_ux,
                "Flutter Multiplatform",
                "Ilham Yudiatmoko",
                "Beginner Level",
                "10 Modul",
                "160 Menit",
                "4.7",
                350000.0
            )
        )
    }
}
