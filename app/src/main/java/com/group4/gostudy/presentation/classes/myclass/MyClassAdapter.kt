package com.group4.gostudy.presentation.classes.myclass

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.group4.gostudy.databinding.MyClassItemBinding
import com.group4.gostudy.model.UserCourse

class MyClassAdapter(
    private val itemClick: (UserCourse) -> Unit
) : RecyclerView.Adapter<ClassItemViewHolder>() {

    private val differ = AsyncListDiffer(
        this,
        object : DiffUtil.ItemCallback<UserCourse>() {
            override fun areItemsTheSame(
                oldItem: UserCourse,
                newItem: UserCourse
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UserCourse,
                newItem: UserCourse
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClassItemViewHolder {
        val binding = MyClassItemBinding.inflate(
            LayoutInflater.from(parent.context)
        )
        return ClassItemViewHolder(binding, itemClick)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(
        holder: ClassItemViewHolder,
        position: Int
    ) {
        holder.bind(differ.currentList[position])
    }

    fun setData(data: List<UserCourse>) {
        differ.submitList(data)
    }
}
