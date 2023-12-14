package com.group4.gostudy.presentation.course.course

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.CourseItemBinding
import com.group4.gostudy.model.PopularCourse

class CourseViewHolder(
    private val binding: CourseItemBinding,
    private val onClicked: (PopularCourse) -> Unit
) : RecyclerView.ViewHolder(binding.root),
    ViewHolderBinder<PopularCourse> {

    override fun bind(item: PopularCourse) {
        binding.tvCategoryName.text = item.category?.name
        binding.tvClassTitle.text = item.name
        binding.tvMentorName.text = String.format("by %s", item.courseBy)
        binding.tvLevel.text = item.level
        binding.tvModule.text = item.totalModule.toString()
        binding.tvDuration.text = item.totalDuration.toString()
        binding.tvRating.text = item.level
        binding.ivMyClass.load(item.imageUrl)
        binding.tvTypeClass.text = item.type
    }
}
