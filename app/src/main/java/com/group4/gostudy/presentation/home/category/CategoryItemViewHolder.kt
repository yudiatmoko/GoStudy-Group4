package com.group4.gostudy.presentation.home.category

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.group4.gostudy.R
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
    private val onClicked: (Category) -> Unit,
    private val adapter: CategoryAdapter
) : RecyclerView.ViewHolder(binding.root),
    ViewHolderBinder<Category> {

    override fun bind(item: Category) {
        binding.tvCategoryName.text = item.name
        binding.ivCategoryImage.load(item.imageUrl)
        binding.root.setOnClickListener {
            onClicked.invoke(item)
        }
        setSelected(adapterPosition == adapter.getSelectedPosition())
    }

    private fun setSelected(selected: Boolean) {
        itemView.isActivated = selected

        val textColor = if (selected) {
            ContextCompat.getColor(itemView.context, R.color.seed)
        } else {
            ContextCompat.getColor(itemView.context, R.color.white)
        }
        binding.tvCategoryName.setTextColor(textColor)
    }
}
class CategoryViewMoreItemViewHolder(
    private val binding: CourseCategoryItemListBinding,
    private val onClicked: (Category) -> Unit,
    private val adapter: CategoryViewMoreAdapter
) : RecyclerView.ViewHolder(binding.root),
    ViewHolderBinder<Category> {

    override fun bind(item: Category) {
        binding.tvCategoryName.text = item.name
        binding.root.setOnClickListener {
            onClicked.invoke(item)
        }
        setSelected(adapterPosition == adapter.getSelectedPosition())
    }

    private fun setSelected(selected: Boolean) {
        itemView.isActivated = selected

        val textColor = if (selected) {
            ContextCompat.getColor(itemView.context, R.color.seed)
        } else {
            ContextCompat.getColor(itemView.context, R.color.white)
        }
        binding.tvCategoryName.setTextColor(textColor)
    }
}
