package com.group4.gostudy.presentation.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.NotificationItemListBinding
import com.group4.gostudy.model.Notification

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class NotificationListAdapter(
    private val itemClick: (Notification) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differ = AsyncListDiffer(
        this,
        object : DiffUtil.ItemCallback<Notification>() {
            override fun areItemsTheSame(
                oldItem: Notification,
                newItem: Notification
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Notification,
                newItem: Notification
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = NotificationItemListBinding.inflate(LayoutInflater.from(parent.context))
        return NotificationItemViewHolder(binding, itemClick)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as ViewHolderBinder<Notification>).bind(differ.currentList[position])
    }

    fun setData(data: List<Notification>) {
        differ.submitList(data)
    }
}
