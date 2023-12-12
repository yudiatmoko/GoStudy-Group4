package com.group4.gostudy.presentation.course.typeofclass

import androidx.recyclerview.widget.RecyclerView
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.CourseCategoryItemListBinding
import com.group4.gostudy.model.TypeOfClass

class TypeOfClassViewHolder(
    private val binding: CourseCategoryItemListBinding,
    private val onClicked: (TypeOfClass) -> Unit
) : RecyclerView.ViewHolder(binding.root),
    ViewHolderBinder<TypeOfClass> {

    override fun bind(item: TypeOfClass) {
        binding.tvCategoryName.text = item.name
        binding.root.setOnClickListener {
            onClicked.invoke(item)
        }
    }
}
