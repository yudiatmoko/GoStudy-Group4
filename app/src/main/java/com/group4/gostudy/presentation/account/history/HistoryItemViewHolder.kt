package com.group4.gostudy.presentation.account.history

import androidx.recyclerview.widget.RecyclerView
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
        binding.tvCourseName.text = item.name
        binding.tvCreatorName.text = item.creator
        binding.tvLevel.text = item.level
        binding.tvModule.text = item.module
        binding.tvDuration.text = item.duration
        binding.tvRating.text = item.rating
        binding.tvPaidStatus.text = item.paidStatus
        binding.ivCourseImg.setImageResource(item.img)
    }
}
