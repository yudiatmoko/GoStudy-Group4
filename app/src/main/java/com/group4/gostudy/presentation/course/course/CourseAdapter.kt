package com.group4.gostudy.presentation.course.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.CourseItemBinding
import com.group4.gostudy.model.PopularCourse

class CourseAdapter(
    private val itemClick: (PopularCourse) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differ = AsyncListDiffer(
        this,
        object : DiffUtil.ItemCallback<PopularCourse>() {
            override fun areItemsTheSame(
                oldItem: PopularCourse,
                newItem: PopularCourse
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PopularCourse,
                newItem: PopularCourse
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = CourseItemBinding.inflate(
            LayoutInflater.from(parent.context)
        )
        return CourseViewHolder(binding, itemClick)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as ViewHolderBinder<PopularCourse>).bind(differ.currentList[position])
    }

    fun setData(data: List<PopularCourse>) {
        differ.submitList(data)
    }
}
