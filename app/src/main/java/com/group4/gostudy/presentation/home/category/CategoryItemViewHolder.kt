package com.group4.gostudy.presentation.home.category

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.CategoryItemListBinding
import com.group4.gostudy.databinding.CourseCategoryItemListBinding
import com.group4.gostudy.model.Category

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class CategoryItemViewHolder(
    private val binding: CategoryItemListBinding,
    private val onClicked: (Category) -> Unit
) : RecyclerView.ViewHolder(binding.root),
    ViewHolderBinder<Category> {

    override fun bind(item: Category) {
        binding.tvCategoryName.text = item.name
        binding.ivCategoryImage.load(item.imageUrl)
        binding.root.setOnClickListener {
            onClicked.invoke(item)
        }
    }
}

class CourseCategoryItemViewHolder(
    private val binding: CourseCategoryItemListBinding,
    private val onClicked: (Category) -> Unit
) : RecyclerView.ViewHolder(binding.root),
    ViewHolderBinder<Category> {

    override fun bind(item: Category) {
        binding.tvCategoryName.text = item.name
        binding.root.setOnClickListener {
            onClicked.invoke(item)
        }
    }
}
