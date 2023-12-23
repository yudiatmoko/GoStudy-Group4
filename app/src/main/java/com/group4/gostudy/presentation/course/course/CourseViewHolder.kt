package com.group4.gostudy.presentation.course.course

import androidx.core.view.isVisible
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
        with(binding) {
            tvCategoryName.text = item.category?.name
            tvClassTitle.text = item.name
            tvMentorName.text = String.format("by %s", item.courseBy)
            tvLevel.text = item.level
            tvModule.text = item.totalModule.toString()
            tvDuration.text = item.totalDuration.toString()
            tvRating.text = item.rating.toString()
            ivMyClass.load(item.imageUrl)
        }
        if (item.type != "Premium") {
            binding.tvTypeClass.text = item.type
        } else {
            binding.tvTypeClass.isVisible = false
            binding.llKindOfClassPremium.isVisible = true
            binding.tvPremium.text = item.type
        }
    }
}
