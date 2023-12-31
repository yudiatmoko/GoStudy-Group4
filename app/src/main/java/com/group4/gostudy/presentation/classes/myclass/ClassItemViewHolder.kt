package com.group4.gostudy.presentation.classes.myclass

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.group4.gostudy.R
import com.group4.gostudy.core.ViewHolderBinder
import com.group4.gostudy.databinding.MyClassItemBinding
import com.group4.gostudy.model.UserCourse
import com.group4.gostudy.utils.formatDurationToMinutes

class ClassItemViewHolder(
    private val binding: MyClassItemBinding,
    private val onClicked: (UserCourse) -> Unit
) : RecyclerView.ViewHolder(binding.root),
    ViewHolderBinder<UserCourse> {

    @SuppressLint("StringFormatInvalid")
    override fun bind(item: UserCourse) {
        binding.piMyClass.progress = item.totalProgress ?: 0

        if (item.totalProgress == 100) {
            binding.tvProgressText.visibility = View.VISIBLE
            binding.tvProgressText.text = binding.root.context.getString(R.string.text_selesai)
        } else if (item.totalProgress!! < 100) {
            binding.tvProgressText.visibility = View.VISIBLE
            binding.tvProgressText.text = "${item.totalProgress}%"
        } else {
            binding.tvProgressText.visibility = View.GONE
        }

        binding.tvCategoryName.text = item.courseX?.category?.name.orEmpty()
        binding.tvClassTitle.text = item.courseX?.name.orEmpty()
        binding.tvMentorName.text = String.format(binding.root.context.getString(R.string.txt_by_s), item.courseX?.courseBy.orEmpty())
        binding.tvLevel.text = item.courseX?.level.orEmpty()
        binding.tvModule.text = item.courseX?.totalModule.toString()
        binding.tvDuration.text = item.courseX?.totalDuration.formatDurationToMinutes()
        binding.tvRating.text = item.courseX?.rating.toString()
        binding.ivMyClass.load(item.courseX?.imageUrl)

        binding.root.setOnClickListener {
            onClicked.invoke(item)
        }
    }
}
