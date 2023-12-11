package com.group4.gostudy.model

data class Chapter(
    val noChapter: String?,
    var courseId: Int?,
    val name: String,
    val data: List<String>,
    val time: String,
    val createdAt: String?,
    val updateAt: String?
)

object DummyDetailCourseMaterialDataSource {

    fun getListData(): List<Chapter> {
        return listOf(
            Chapter(
                "1", 1, "Pendahuluan", listOf(
                    "Tujuan mengikuti kelas Design System",
                    "Pengenalan Design System",
                    "Contoh dalam membagun Design System"
                ),"120 menit", "9-12-2023", "9-12-2023"
            ),
            Chapter(
                "2", 2, "Memulai Design", listOf(
                    "Color Palette",
                    "Typography",
                    "Layout dan Grid",
                    "Button Component"
                ),"60 menit", "9-12-2023", "9-12-2023"
            )
        )
    }

}
