package com.group4.gostudy.presentation.course.course

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.group4.gostudy.R
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.CourseItemBinding
import com.group4.gostudy.model.Course
import com.group4.gostudy.utils.formatDurationToMinutes

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
            binding.tvMentorName.text = String.format(binding.root.context.getString(R.string.txt_by_s), item.courseBy.orEmpty())
            tvLevel.text = item.level
            tvModule.text = item.totalModule.toString()
            tvDuration.text = item.totalDuration.formatDurationToMinutes()
            tvRating.text = item.rating.toString()
            ivMyClass.load(item.imageUrl)
        }
        if (item.type != binding.root.context.getString(R.string.txt_premium)
        ) {
            binding.tvTypeClass.text = item.type
        } else {
            binding.tvTypeClass.isVisible = false
            binding.llKindOfClassPremium.isVisible = true
            binding.tvPremium.text = item.type
        }
    }
}
