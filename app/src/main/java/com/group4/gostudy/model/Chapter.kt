package com.group4.gostudy.model

data class Chapter(
    val courseId: Int?,
    val createdAt: String?,
    val id: Int?,
    val data: List<String>,
    val name: String,
    val noChapter: Int?,
    val updatedAt: String?
)

/*object DummyDetailCourseMaterialDataSource {

    fun getListData(): List<Chapter> {
        return listOf(
            Chapter(
                "1",
                1,
                "Pendahuluan",
                listOf(
                    "Tujuan mengikuti kelas Design System",
                    "Pengenalan Design System",
                    "Contoh dalam membagun Design System"
                ),
                "120 menit",
                "9-12-2023",
                "9-12-2023"
            ),
            Chapter(
                "2",
                2,
                "Memulai Design",
                listOf(
                    "Color Palette",
                    "Typography",
                    "Layout dan Grid",
                    "Button Component"
                ),
                "60 menit",
                "9-12-2023",
                "9-12-2023"
            )
        )
    }
}*/
