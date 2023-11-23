package com.group4.gostudy.presentation.account.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.HistoryItemListBinding
import com.group4.gostudy.model.History

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class HistoryListAdapter(
    private val itemClick: (History) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differ = AsyncListDiffer(
        this,
        object : DiffUtil.ItemCallback<History>() {
            override fun areItemsTheSame(
                oldItem: History,
                newItem: History
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: History,
                newItem: History
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = HistoryItemListBinding.inflate(
            LayoutInflater.from(parent.context)
        )
        return HistoryItemViewHolder(binding, itemClick)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as ViewHolderBinder<History>).bind(differ.currentList[position])
    }

    fun setData(data: List<History>) {
        differ.submitList(data)
    }
}
