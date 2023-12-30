package com.group4.gostudy.presentation.notification

import androidx.recyclerview.widget.RecyclerView
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.NotificationItemListBinding
import com.group4.gostudy.model.AllNotif

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class NotificationItemViewHolder(
    private val binding: NotificationItemListBinding,
    private val onClicked: (AllNotif) -> Unit,
    private val adapter: NotificationListAdapter
) : RecyclerView.ViewHolder(binding.root),
    ViewHolderBinder<AllNotif> {

    override fun bind(item: AllNotif) {
        binding.tvNotificationTitle.text = item.notification?.category
        binding.tvNotificationSub.text = item.notification?.title
        binding.tvNotificationContain.text = item.notification?.description
        setSelected(adapterPosition == adapter.getSelectedPosition())
    }

    private fun setSelected(selected: Boolean) {
        itemView.isActivated = selected

        var maxLines: Int? = null
        if (selected) {
            maxLines = 50
        } else {
            maxLines = 3
        }
        binding.tvNotificationContain.maxLines = maxLines
    }
}
