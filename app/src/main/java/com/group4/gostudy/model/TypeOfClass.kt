package com.group4.gostudy.model

data class TypeOfClass(
    val id: Int,
    val name: String,
    val slug: String
)

object TypeOfClassProvider {
    fun getDummyData(): List<TypeOfClass> {
        return listOf(
            TypeOfClass(
                1,
                "all",
                "all"
            ),
            TypeOfClass(
                2,
                "Kelas Premium",
                "kelas premium"
            ),
            TypeOfClass(
                3,
                "Kelas Gratis",
                "kelas Gratis"
            )
        )
    }
}
