package com.group4.gostudy.presentation.home.popularcourse

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.PopularCourseItemBinding
import com.group4.gostudy.model.Course

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class PopularCourseItemViewHolder(
    private val binding: PopularCourseItemBinding,
    private val onClicked: (Course) -> Unit
) : RecyclerView.ViewHolder(binding.root),
    ViewHolderBinder<Course> {

    override fun bind(item: Course) {
        binding.tvCategoryName.text = item.category
        binding.tvCourseTitle.text = item.title
        binding.tvInstructorName.text = String.format("by %s", item.instructor)
        binding.tvLevel.text = item.level
        binding.tvModule.text = item.modules
        binding.tvDuration.text = item.duration
        binding.tvRating.text = item.rating
        binding.tvPrice.text = String.format("Beli Rp. %.0f", item.price)
        binding.ivCourseImg.load(item.img)
    }
}
