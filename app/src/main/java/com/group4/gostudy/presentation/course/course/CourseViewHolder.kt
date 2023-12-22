package com.group4.gostudy.presentation.course.course

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.CourseItemBinding
import com.group4.gostudy.model.Course

class CourseViewHolder(
    private val binding: CourseItemBinding,
    private val onClicked: (Course) -> Unit
) : RecyclerView.ViewHolder(binding.root),
    ViewHolderBinder<Course> {

    override fun bind(item: Course) {
        binding.root.setOnClickListener {
            onClicked.invoke(item)
        }
        binding.tvCategoryName.text = item.category?.name
        binding.tvClassTitle.text = item.name
        binding.tvMentorName.text = String.format("by %s", item.courseBy)
        binding.tvLevel.text = item.level
        binding.tvModule.text = item.totalModule.toString()
        binding.tvDuration.text = item.totalDuration.toString()
        binding.tvRating.text = item.rating.toString()
        binding.ivMyClass.load(item.imageUrl)
        binding.tvTypeClass.text = item.type
    }
}
