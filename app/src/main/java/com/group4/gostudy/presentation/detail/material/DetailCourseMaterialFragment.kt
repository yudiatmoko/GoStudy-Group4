package com.group4.gostudy.presentation.detail.material

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.group4.gostudy.databinding.FragmentDetailCourseMaterialBinding
import com.group4.gostudy.model.Chapter
import com.group4.gostudy.utils.ApiException
import com.group4.gostudy.utils.proceedWhen
import com.group4.gostudy.viewitem.DataItem
import com.group4.gostudy.viewitem.HeaderItem
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailCourseMaterialFragment : Fragment() {
    private lateinit var binding: FragmentDetailCourseMaterialBinding

    private val adapter: GroupieAdapter by lazy {
        GroupieAdapter()
    }
    private val detailMaterialViewModel: DetailMaterialViewModel by viewModel()

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
        observeChapter()
    }

    private fun setData(chapters: List<Chapter>) {
        binding.rvData.adapter = adapter
        val sections = chapters.map { sectionData ->
            val section = Section()
            section.setHeader(
                HeaderItem(sectionData.name) {
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

    private fun observeChapter() {
        detailMaterialViewModel.getChapter()
        detailMaterialViewModel.chapters.observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnLoading = {
                    binding.layoutStateChapter.root.isVisible =
                        true
                    binding.layoutStateChapter.animLoading.isVisible =
                        true
                    binding.layoutStateChapter.llAnimError.isVisible =
                        false
                    binding.rvData.isVisible =
                        false
                },
                doOnSuccess = { result ->
                    binding.layoutStateChapter.root.isVisible =
                        true
                    binding.layoutStateChapter.animLoading.isVisible =
                        false
                    binding.layoutStateChapter.llAnimError.isVisible =
                        false
                    binding.rvData.isVisible = true
                    result.payload?.let {
                        setData(it)
                    }
                },
                doOnError = {
                    binding.layoutStateChapter.root.isVisible =
                        true
                    binding.layoutStateChapter.animLoading.isVisible =
                        false
                    binding.layoutStateChapter.llAnimError.isVisible =
                        true
                    binding.rvData.isVisible =
                        false
                    if (it.exception is ApiException) {
                        binding.layoutStateChapter.tvError.isVisible =
                            true
                        binding.layoutStateChapter.tvError.text =
                            it.exception.getParsedError()?.message
                    }
                }
            )
        }
    }
}
