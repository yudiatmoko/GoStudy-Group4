package com.group4.gostudy.presentation.detailcourse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.group4.gostudy.databinding.FragmentDetailCourseMaterialBinding
import com.group4.gostudy.model.DummyDetailCourseMaterialDataSource
import com.group4.gostudy.viewitem.DataItem
import com.group4.gostudy.viewitem.HeaderItem
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section

class DetailCourseMaterialFragment : Fragment() {
    private lateinit var binding: FragmentDetailCourseMaterialBinding

    private val adapter: GroupieAdapter by lazy {
        GroupieAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailCourseMaterialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun setData() {
        binding.rvData.adapter = adapter
        val sections = DummyDetailCourseMaterialDataSource.getListData().map { sectionData ->
            val section = Section()
            section.setHeader(
                HeaderItem(sectionData.name, sectionData.time) {
                    Toast.makeText(
                        requireContext(),
                        "Header Clicked: ${sectionData.name}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            )
            val items = sectionData.data.map { data ->
                DataItem(data) {
                    Toast.makeText(requireContext(), "Item Clicked: $data", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            section.addAll(items)
            section
        }
        adapter.addAll(sections)
    }
}
