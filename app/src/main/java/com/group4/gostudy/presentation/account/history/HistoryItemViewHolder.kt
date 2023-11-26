package com.group4.gostudy.presentation.account.history

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.HistoryItemListBinding
import com.group4.gostudy.model.History

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class HistoryItemViewHolder(
    private val binding: HistoryItemListBinding,
    private val onClicked: (History) -> Unit
) : RecyclerView.ViewHolder(binding.root),
    ViewHolderBinder<History> {

    override fun bind(item: History) {
        binding.tvCategoryName.text = item.category
        binding.tvCourseTitle.text = item.title
        binding.tvInstructorName.text = String.format("by %s", item.instructor)
        binding.tvLevel.text = item.level
        binding.tvModule.text = item.modules
        binding.tvDuration.text = item.duration
        binding.tvRating.text = item.rating
        if (item.paidStatus) {
            binding.tvPaidStatusTrue.isVisible = true
        } else {
            binding.tvPaidStatusTrue.isVisible = false
            binding.tvPaidStatusFalse.isVisible = true
        }
        binding.ivCourseImg.load(item.img)
    }
}
