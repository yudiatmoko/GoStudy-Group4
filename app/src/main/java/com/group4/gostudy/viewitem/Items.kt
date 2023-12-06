package com.group4.gostudy.viewitem

import android.view.View
import com.group4.gostudy.R
import com.group4.gostudy.databinding.ItemSectionDataBinding
import com.group4.gostudy.databinding.ItemSectionHeaderBinding
import com.xwray.groupie.viewbinding.BindableItem

class HeaderItem(private val title: String, private val timeDuration: String, private val onHeaderClick: (item: String) -> Unit) :
    BindableItem<ItemSectionHeaderBinding>() {
    override fun bind(viewBinding: ItemSectionHeaderBinding, position: Int) {
        viewBinding.tvHeaderSectionName.text = title
        viewBinding.tvHeaderSectionTime.text = timeDuration
        viewBinding.root.setOnClickListener { onHeaderClick.invoke(title) }
    }

    override fun getLayout(): Int = R.layout.item_section_header

    override fun initializeViewBinding(view: View): ItemSectionHeaderBinding =
        ItemSectionHeaderBinding.bind(view)
}

class DataItem(private val data: String, private val onItemClick: (item: String) -> Unit) :
    BindableItem<ItemSectionDataBinding>() {
    override fun bind(viewBinding: ItemSectionDataBinding, position: Int) {
        viewBinding.tvSectionData.text = data
        viewBinding.root.setOnClickListener { onItemClick.invoke(data) }
    }

    override fun getLayout(): Int = R.layout.item_section_data

    override fun initializeViewBinding(view: View): ItemSectionDataBinding =
        ItemSectionDataBinding.bind(view)
}
