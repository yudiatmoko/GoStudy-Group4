package com.group4.gostudy.viewitem

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.group4.gostudy.R
import com.group4.gostudy.databinding.ItemSectionDataBinding
import com.group4.gostudy.databinding.ItemSectionHeaderBinding
import com.group4.gostudy.model.Module
import com.group4.gostudy.utils.formatDurationToMinutes
import com.xwray.groupie.viewbinding.BindableItem

class HeaderItem(
    private val title: String,
    private val duration: Int
) : BindableItem<ItemSectionHeaderBinding>() {
    override fun bind(viewBinding: ItemSectionHeaderBinding, position: Int) {
        viewBinding.tvHeaderSectionName.text = title
        viewBinding.tvHeaderSectionTime.text = duration.formatDurationToMinutes().plus(" Menit")
    }

    override fun getLayout(): Int = R.layout.item_section_header

    override fun initializeViewBinding(view: View): ItemSectionHeaderBinding =
        ItemSectionHeaderBinding.bind(view)
}

class DataItem(
    private val context: Context,
    private val number: String,
    private val data: Module,
    private val title: String,
    private val isUnlocked: Boolean?,
    private val onItemClick: (item: Module) -> Unit
) :
    BindableItem<ItemSectionDataBinding>() {
    override fun bind(viewBinding: ItemSectionDataBinding, position: Int) {
        viewBinding.tvModuleNumber.text = number
        viewBinding.tvSectionData.text = title

        if (isUnlocked == false) {
            viewBinding.ivPlay.isVisible = false
            viewBinding.ivNotPlay.isVisible = false
            viewBinding.ivLock.isVisible = true
            viewBinding.ivLock.setOnClickListener {
                Toast.makeText(
                    context,
                    "Anda belum  membeli course ini!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            viewBinding.root.setOnClickListener { onItemClick.invoke(data) }
        }
    }

    override fun getLayout(): Int = R.layout.item_section_data

    override fun initializeViewBinding(view: View): ItemSectionDataBinding =
        ItemSectionDataBinding.bind(view)
}
