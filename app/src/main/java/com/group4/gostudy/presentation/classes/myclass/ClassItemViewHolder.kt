package com.group4.gostudy.presentation.classes.myclass

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.MyClassItemBinding
import com.group4.gostudy.model.Course

class ClassItemViewHolder(
    private val binding: MyClassItemBinding,
    private val onClicked: (Course) -> Unit
) : RecyclerView.ViewHolder(binding.root),
    ViewHolderBinder<Course> {

    override fun bind(item: Course) {
        binding.tvCategoryName.text = item.category
        binding.tvClassTitle.text = item.title
        binding.tvMentorName.text = String.format("by %s", item.instructor)
        binding.tvLevel.text = item.level
        binding.tvModule.text = item.modules
        binding.tvDuration.text = item.duration
        binding.tvRating.text = item.rating
        binding.ivMyClass.load(item.img)
    }
}
