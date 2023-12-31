package com.group4.gostudy.presentation.home.popularcourse

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.group4.gostudy.R
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.PopularCourseItemBinding
import com.group4.gostudy.databinding.PopularCourseViewMoreItemBinding
import com.group4.gostudy.model.Course
import com.group4.gostudy.utils.formatDurationToMinutes

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
        binding.root.setOnClickListener {
            onClicked.invoke(item)
        }
        binding.tvCategoryName.text = item.category?.name
        binding.tvCourseTitle.text = item.name
        binding.tvInstructorName.text = String.format("by %s", item.courseBy)
        binding.tvLevel.text = item.level
        binding.tvModule.text = String.format("%.0f Modul", item.totalModule?.toDouble())
        binding.tvDuration.text = item.totalDuration?.formatDurationToMinutes().plus(" Menit")
        binding.tvRating.text = item.rating.toString()
        if ((item.price ?: 0) > 0) {
            binding.tvPrice.text = String.format("Beli Rp. %.0f", item.price?.toDouble())
        } else {
            binding.tvPrice.text = itemView.context.getString(R.string.free_class)
        }
        binding.ivCourseImg.load(item.imageUrl)
    }
}

class PopularCourseViewMoreItemViewHolder(
    private val binding: PopularCourseViewMoreItemBinding,
    private val onClicked: (Course) -> Unit
) : RecyclerView.ViewHolder(binding.root),
    ViewHolderBinder<Course> {

    override fun bind(item: Course) {
        binding.root.setOnClickListener {
            onClicked.invoke(item)
        }

        binding.tvCategoryName.text = item.category?.name
        binding.tvCourseTitle.text = item.name
        binding.tvInstructorName.text = String.format("by %s", item.courseBy)
        binding.tvLevel.text = item.level
        binding.tvModule.text = String.format("%.0f Modul", item.totalModule?.toDouble())
        binding.tvDuration.text = String.format("%.0f Menit", item.totalDuration?.toDouble())
        binding.tvRating.text = item.rating.toString()
        if ((item.price ?: 0) > 0) {
            binding.tvPrice.text = String.format("Beli Rp. %.0f", item.price?.toDouble())
        } else {
            binding.tvPrice.text = itemView.context.getString(R.string.free_class)
        }
        binding.ivCourseImg.load(item.imageUrl)
    }
}
