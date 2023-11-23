package com.group4.gostudy.presentation.notification

import androidx.recyclerview.widget.RecyclerView
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.NotificationItemListBinding
import com.group4.gostudy.model.Notification

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class NotificationItemViewHolder(
    private val binding: NotificationItemListBinding,
    private val onClicked: (Notification) -> Unit
) : RecyclerView.ViewHolder(binding.root),
    ViewHolderBinder<Notification> {

    override fun bind(item: Notification) {
        binding.tvNotificationTitle.text = item.title
        binding.tvNotificationSub.text = item.subtitle
        binding.tvNotificationContain.text = item.content
    }
}
