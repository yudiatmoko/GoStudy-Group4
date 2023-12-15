package com.group4.gostudy.presentation.home.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.group4.gostudy.databinding.CategoryItemListBinding
import com.group4.gostudy.databinding.CourseCategoryItemListBinding
import com.group4.gostudy.model.Category

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class CategoryAdapter(
    private val itemClick: (Category) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedPosition: Int = RecyclerView.NO_POSITION

    fun getSelectedPosition(): Int {
        return selectedPosition
    }

    private val differ = AsyncListDiffer(
        this,
        object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(
                oldItem: Category,
                newItem: Category
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Category,
                newItem: Category
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = CategoryItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryItemViewHolder(
            binding,
            itemClick,
            this
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val viewHolder = holder as CategoryItemViewHolder
        val category = differ.currentList[position]
        viewHolder.bind(category)

        viewHolder.itemView.setOnClickListener {
            selectedPosition = holder.adapterPosition
            notifyDataSetChanged()
            itemClick(category)
        }
    }

    fun setData(data: List<Category>) {
        differ.submitList(data)
    }
}

class CategoryViewMoreAdapter(
    private val itemClick: (Category) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedPosition: Int = RecyclerView.NO_POSITION

    fun getSelectedPosition(): Int {
        return selectedPosition
    }

    private val differ = AsyncListDiffer(
        this,
        object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(
                oldItem: Category,
                newItem: Category
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Category,
                newItem: Category
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = CourseCategoryItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewMoreItemViewHolder(
            binding,
            itemClick,
            this
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val viewHolder = holder as CategoryViewMoreItemViewHolder
        val category = differ.currentList[position]
        viewHolder.bind(category)

        viewHolder.itemView.setOnClickListener {
            selectedPosition = holder.adapterPosition
            notifyDataSetChanged()
            itemClick(category)
        }
    }

    fun setData(data: List<Category>) {
        differ.submitList(data)
    }
}
