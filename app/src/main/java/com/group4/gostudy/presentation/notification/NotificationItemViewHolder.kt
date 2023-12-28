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
    private val onClicked: (AllNotif) -> Unit
) : RecyclerView.ViewHolder(binding.root),
    ViewHolderBinder<AllNotif> {

    override fun bind(item: AllNotif) {
        binding.tvNotificationTitle.text = item.notification?.category
        binding.tvNotificationSub.text = item.notification?.title
        binding.tvNotificationContain.text = item.notification?.description
    }
}
