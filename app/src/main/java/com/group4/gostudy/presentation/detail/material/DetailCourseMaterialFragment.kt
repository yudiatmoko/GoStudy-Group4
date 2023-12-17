package com.group4.gostudy.presentation.detail.material

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.group4.gostudy.databinding.FragmentDetailCourseMaterialBinding
import com.group4.gostudy.model.Chapter
import com.group4.gostudy.model.Module
import com.group4.gostudy.model.SectionedData
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
    private val materialViewModel: MaterialViewModel by viewModel()


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

    private fun observeChapter() {
        materialViewModel.getChapter()
        materialViewModel.chapters.observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = { chapterResult ->
                    binding.layoutStateChapter.root.isVisible =
                        true
                    binding.layoutStateChapter.animLoading.isVisible =
                        false
                    binding.layoutStateChapter.llAnimError.isVisible =
                        false
                    binding.rvData.isVisible = true
                    chapterResult.payload?.let { chapters ->
                        setData(chapters, null)
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

    private fun setData(chapters: List<Chapter>?, module: List<Module>?) {
        binding.rvData.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapter
        }
        val section = getListData(chapters, module).map {
            val section = Section()
            section.setHeader(
                HeaderItem(it.dataHeader?.get(0)?.name.orEmpty()) { data ->
                    Toast.makeText(requireContext(), "Header Clicked : $data", Toast.LENGTH_SHORT)
                        .show()
                }
            )
            val dataSection = it.dataItem?.map { data ->
                DataItem(data.name) { data ->
                    Toast.makeText(requireContext(), "Item Clicked : $data", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            if (dataSection != null) {
                section.addAll(dataSection)
            }
            section
        }
        adapter.addAll(section)
    }

    private fun getListData(chapters: List<Chapter>?, module: List<Module>?): List<SectionedData> =
        listOf(
            SectionedData(chapters, module)
        )
}
