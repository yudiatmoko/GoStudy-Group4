package com.group4.gostudy.presentation.classes.progresscategory

import androidx.recyclerview.widget.RecyclerView
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.CategoryProgressItemBinding
import com.group4.gostudy.model.ProgressCategory

class CategoryProgressViewHolder(
    private val binding: CategoryProgressItemBinding,
    private val onClicked: (ProgressCategory) -> Unit
) : RecyclerView.ViewHolder(binding.root),
    ViewHolderBinder<ProgressCategory> {

    override fun bind(item: ProgressCategory) {
        binding.tvCategoryProgressName.text = item.name
        binding.root.setOnClickListener {
            onClicked.invoke(item)
        }
    }
}
