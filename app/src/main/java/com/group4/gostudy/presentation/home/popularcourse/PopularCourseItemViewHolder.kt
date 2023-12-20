package com.group4.gostudy.presentation.home.popularcourse

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.PopularCourseItemBinding
import com.group4.gostudy.databinding.PopularCourseViewMoreItemBinding
import com.group4.gostudy.model.PopularCourse

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class PopularCourseItemViewHolder(
    private val binding: PopularCourseItemBinding,
    private val onClicked: (PopularCourse) -> Unit
) : RecyclerView.ViewHolder(binding.root),
    ViewHolderBinder<PopularCourse> {

    override fun bind(item: PopularCourse) {
        binding.root.setOnClickListener {
            onClicked.invoke(item)
        }
        binding.tvCategoryName.text = item.category?.name
        binding.tvCourseTitle.text = item.name
        binding.tvInstructorName.text = String.format("by %s", item.courseBy)
        binding.tvLevel.text = item.level
        binding.tvModule.text = String.format("%.0f Modul", item.totalModule?.toDouble())
        binding.tvDuration.text = String.format("%.0f Menit", item.totalDuration?.toDouble())
        binding.tvRating.text = ""
        binding.tvPrice.text = String.format("Beli Rp. %.0f", item.price?.toDouble())
        binding.ivCourseImg.load(item.imageUrl)
    }
}

class PopularCourseViewMoreItemViewHolder(
    private val binding: PopularCourseViewMoreItemBinding,
    private val onClicked: (PopularCourse) -> Unit
) : RecyclerView.ViewHolder(binding.root),
    ViewHolderBinder<PopularCourse> {

    override fun bind(item: PopularCourse) {
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
        binding.tvPrice.text = String.format("Beli Rp. %.0f", item.price?.toDouble())
        binding.ivCourseImg.load(item.imageUrl)
    }
}
