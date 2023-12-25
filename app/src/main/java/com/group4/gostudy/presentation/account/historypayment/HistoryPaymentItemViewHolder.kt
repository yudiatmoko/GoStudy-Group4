package com.group4.gostudy.presentation.account.historypayment

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.HistoryItemListBinding
import com.group4.gostudy.model.HistoryPayment

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class HistoryPaymentItemViewHolder(
    private val binding: HistoryItemListBinding,
    private val onClicked: (HistoryPayment) -> Unit
) : RecyclerView.ViewHolder(binding.root),
    ViewHolderBinder<HistoryPayment> {

    override fun bind(item: HistoryPayment) {
        binding.tvCategoryName.text = item.course?.category?.name
        binding.tvCourseTitle.text = item.course?.name
        binding.tvInstructorName.text = String.format("by %s", item.course?.courseBy)
        binding.tvLevel.text = item.course?.level
        binding.tvModule.text = String.format("%.0f Modul", item.course?.totalModule?.toDouble())
        binding.tvDuration.text = String.format("%.0f Menit", item.course?.totalDuration?.toDouble())
        binding.tvRating.text = item.course?.rating.toString()
        binding.ivCourseImg.load(item.course?.imageUrl)
        if (item.status == "paid") {
            binding.tvPaidStatusTrue.isVisible = true
        } else {
            binding.tvPaidStatusTrue.isVisible = false
            binding.tvPaidStatusFalse.isVisible = true
        }
    }
}
