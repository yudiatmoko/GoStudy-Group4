package com.group4.gostudy.model

data class History(
    val id: Int,
    val category: String,
    val img: String,
    val title: String,
    val instructor: String,
    val level: String,
    val modules: String,
    val duration: String,
    val rating: String,
    val paidStatus: Boolean
)

object HistoryProvider {
    fun getDummyData(): List<History> {
        return listOf(
//            History(
//                UUID.randomUUID(),
//                "UI/UX Design",
//                R.drawable.img_ui_ux,
//                "Belajar Web Design dengan Figma",
//                "by Angela Doe",
//                "Intermediate Level",
//                "12 Modul",
//                "90 Menit",
//                "4.9",
//                "Paid"
//            ),
//            History(
//                UUID.randomUUID(),
//                "Mobile Developer",
//                R.drawable.img_ui_ux,
//                "Android Engineering",
//                "by Hermas Yuda",
//                "Beginner Level",
//                "12 Modul",
//                "120 Menit",
//                "4.9",
//                "Paid"
//            ),
//            History(
//                UUID.randomUUID(),
//                "Mobile Developer",
//                R.drawable.img_ui_ux,
//                "Flutter Multiplatform",
//                "by Ilham Yudiatmoko",
//                "Beginner Level",
//                "10 Modul",
//                "160 Menit",
//                "4.7",
//                "Paid"
//            )
        )
    }
}
