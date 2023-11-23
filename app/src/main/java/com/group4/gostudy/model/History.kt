package com.group4.gostudy.model

import com.group4.gostudy.R
import java.util.UUID

data class History(
    val id: UUID,
    val category: String,
    val img: Int,
    val name: String,
    val creator: String,
    val level: String,
    val module: String,
    val duration: String,
    val rating: String,
    val paidStatus: String
)

object HistoryProvider {
    fun getDummyData(): List<History> {
        return listOf(
            History(
                UUID.randomUUID(),
                "UI/UX Design",
                R.drawable.img_ui_ux,
                "Belajar Web Design dengan Figma",
                "by Angela Doe",
                "Intermediate Level",
                "12 Modul",
                "90 Menit",
                "4.9",
                "Paid"
            ),
            History(
                UUID.randomUUID(),
                "Mobile Developer",
                R.drawable.img_ui_ux,
                "Android Engineering",
                "by Hermas Yuda",
                "Beginner Level",
                "12 Modul",
                "120 Menit",
                "4.9",
                "Paid"
            ),
            History(
                UUID.randomUUID(),
                "Mobile Developer",
                R.drawable.img_ui_ux,
                "Flutter Multiplatform",
                "by Ilham Yudiatmoko",
                "Beginner Level",
                "10 Modul",
                "160 Menit",
                "4.7",
                "Paid"
            )
        )
    }
}
