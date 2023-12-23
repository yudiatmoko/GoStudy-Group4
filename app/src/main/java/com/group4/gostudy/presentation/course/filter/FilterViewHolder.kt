package com.group4.gostudy.presentation.course.filter

import androidx.recyclerview.widget.RecyclerView
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.ItemFilterCategoryBinding
import com.group4.gostudy.model.Category

class FilterViewHolder(
    private val binding: ItemFilterCategoryBinding,
    private val onItemClick: (Category) -> Unit
) : RecyclerView.ViewHolder(binding.root), ViewHolderBinder<Category> {

    override fun bind(category: Category) {
        binding.cbFilterCategory.text = category.name
        binding.cbFilterCategory.isChecked = category.isChecked

        binding.cbFilterCategory.setOnCheckedChangeListener { _, isChecked ->
            category.isChecked = isChecked
            onItemClick(category)
        }
    }
}
