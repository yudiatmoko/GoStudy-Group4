package com.group4.gostudy.data.dummy

import com.group4.gostudy.model.SectionedData

interface DummyDetailCourseMaterialDataSource {
    fun getListData(): List<SectionedData>
}

class DummyDetailCourseMaterialDataSourceImpl() : DummyDetailCourseMaterialDataSource {
    override fun getListData(): List<SectionedData> = listOf(
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