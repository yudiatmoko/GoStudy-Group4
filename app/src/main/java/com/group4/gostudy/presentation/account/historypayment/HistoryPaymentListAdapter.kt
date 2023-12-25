package com.group4.gostudy.presentation.account.historypayment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.HistoryItemListBinding
import com.group4.gostudy.model.HistoryPayment

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class HistoryPaymentListAdapter(
    private val itemClick: (HistoryPayment) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differ = AsyncListDiffer(
        this,
        object : DiffUtil.ItemCallback<HistoryPayment>() {
            override fun areItemsTheSame(
                oldItem: HistoryPayment,
                newItem: HistoryPayment
            ): Boolean {
                return oldItem.course?.id == newItem.course?.id
            }

            override fun areContentsTheSame(
                oldItem: HistoryPayment,
                newItem: HistoryPayment
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
        return HistoryPaymentItemViewHolder(binding, itemClick)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as ViewHolderBinder<HistoryPayment>).bind(differ.currentList[position])
    }

    fun setData(data: List<HistoryPayment>) {
        differ.submitList(data)
    }
}
