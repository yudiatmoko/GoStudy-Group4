package com.group4.gostudy.model

data class SectionedData(
    val name: String,
    val time: String,
    val data: List<String>
)

object DummyDetailCourseMaterialDataSource {
    fun getListData(): List<SectionedData> {
        return listOf(
            SectionedData(
                "Chapter 1 - Pendahuluan", "60 menit", listOf(
                    "Tujuan mengikuti kelas Design System",
                    "Pengenalan Design System",
                    "Contoh dalam membagun Design System"
                )
            ),
            SectionedData(
                "Chapter 2 - Memulai Desain", "120 menit", listOf(
                    "Color Palette",
                    "Typography",
                    "Layout dan Grid",
                    "Button Component"
                )
            ),
            SectionedData(
                "Chapter 3 - Workflow", "120", listOf(
                    "Color Palette",
                    "Typography",
                    "Layout dan Grid",
                    "Button Component"
                )
            ),
        )
    }
}
