package com.group4.gostudy.model

data class ProgressCategory(
    val id: Int,
    val name: String,
    val slug: String
)

object ProgressCategoryProvider {
    fun getDummyData(): List<ProgressCategory> {
        return listOf(
            ProgressCategory(
                id = 1,
                name = "All",
                slug = "all"
            ),
            ProgressCategory(
                id = 2,
                name = "In Progress",
                slug = "in progress"
            ),
            ProgressCategory(
                id = 1,
                name = "Done",
                slug = "done"
            )
        )
    }
}
