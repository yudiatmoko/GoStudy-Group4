package com.group4.gostudy.presentation.course.typeofclass

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.CourseCategoryItemListBinding
import com.group4.gostudy.model.TypeOfClass

class TypeOfClassAdapter(
    private val itemClick: (TypeOfClass) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            CourseCategoryItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TypeOfClassViewHolder(binding, itemClick)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderBinder<TypeOfClass>).bind(differ.currentList[position])
    }

    private val differ = AsyncListDiffer(
        this,
        object : DiffUtil.ItemCallback<TypeOfClass>() {
            override fun areItemsTheSame(
                oldItem: TypeOfClass,
                newItem: TypeOfClass
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TypeOfClass,
                newItem: TypeOfClass
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    )
    fun setData(data: List<TypeOfClass>) {
        differ.submitList(data)
    }
}
