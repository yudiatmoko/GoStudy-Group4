package com.group4.gostudy.model

import com.group4.gostudy.R

data class Category(
    val id: Int,
    val name: String,
    val imgSrc: Int,
    val slug: String
)

object CategoryProvider {
    fun getDummyData(): List<Category> {
        return listOf(
            Category(
                id = 1,
                name = "UI/UX Designer",
                imgSrc = R.drawable.img_ui_ux,
                slug = "ui/ux designer"
            ),
            Category(
                id = 2,
                name = "Web Developer",
                imgSrc = R.drawable.img_ui_ux,
                slug = "web developer"
            ),
            Category(
                id = 3,
                name = "Product Management",
                imgSrc = R.drawable.img_ui_ux,
                slug = "product management"
            ),
            Category(
                id = 2,
                name = "Mobile Developer",
                imgSrc = R.drawable.img_ui_ux,
                slug = "mobile developer"
            )
        )
    }
}
