package com.group4.gostudy.model

data class Category(
    val createdAt: String?,
    val id: Int?,
    val imageId: String?,
    val imageUrl: String?,
    val name: String?,
    val slug: String?,
    val updatedAt: String?
)

/*object CategoryProvider {
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
}*/
