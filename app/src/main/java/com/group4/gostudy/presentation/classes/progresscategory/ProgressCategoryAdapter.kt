package com.group4.gostudy.presentation.classes.progresscategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.CategoryProgressItemBinding
import com.group4.gostudy.model.ProgressCategory

class ProgressCategoryAdapter(
    private val itemClick: (ProgressCategory) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            CategoryProgressItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryProgressViewHolder(binding, itemClick)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderBinder<ProgressCategory>).bind(differ.currentList[position])
    }

    private val differ = AsyncListDiffer(
        this,
        object : DiffUtil.ItemCallback<ProgressCategory>() {
            override fun areItemsTheSame(
                oldItem: ProgressCategory,
                newItem: ProgressCategory
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ProgressCategory,
                newItem: ProgressCategory
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    )
    fun setData(data: List<ProgressCategory>) {
        differ.submitList(data)
    }
}
