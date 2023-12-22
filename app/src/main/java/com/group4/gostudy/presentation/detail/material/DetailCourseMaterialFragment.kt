package com.group4.gostudy.presentation.detail.material

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.group4.gostudy.databinding.FragmentDetailCourseMaterialBinding
import com.group4.gostudy.model.Chapter
import com.group4.gostudy.presentation.detail.DetailViewModel
import com.group4.gostudy.utils.ApiException
import com.group4.gostudy.utils.proceedWhen
import com.group4.gostudy.viewitem.DataItem
import com.group4.gostudy.viewitem.HeaderItem
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailCourseMaterialFragment : Fragment() {
    private lateinit var binding: FragmentDetailCourseMaterialBinding

    private val groupieAdapter: GroupieAdapter by lazy {
        GroupieAdapter()
    }
    private val materialViewModel: MaterialViewModel by viewModel()

    private val detailViewModel: DetailViewModel by viewModel { parametersOf(requireActivity().intent?.extras) }

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
        detailViewModel.idCourse?.let {
            materialViewModel.getChaptersV2(
                it
            )
        }
        materialViewModel.chapter.observe(viewLifecycleOwner) {
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
                doOnSuccess = { chapterResult ->
                    binding.layoutStateChapter.root.isVisible =
                        true
                    binding.layoutStateChapter.animLoading.isVisible =
                        false
                    binding.layoutStateChapter.llAnimError.isVisible =
                        false
                    binding.rvData.isVisible = true
                    chapterResult.payload?.let { chapters ->
                        setData(chapters)
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

    private fun setData(chapters: List<Chapter>) {
        binding.rvData.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = groupieAdapter
        }
        val section = chapters.map {
            val section = Section()
            section.setHeader(
                HeaderItem(
                    it.name.orEmpty(),
                    it.totalDuration ?: 0
                )
            )
            val dataSection = it.modules?.map { data ->
                DataItem(data.name.orEmpty()) {
                    showDialogOrder()
                }
            }
            if (dataSection != null) {
                section.addAll(dataSection)
            }
            section
        }
        groupieAdapter.addAll(section)
    }

    private fun showDialogOrder() {
//        DialogOrderFragment().show(childFragmentManager, "DialogOrderFragment")
    }
}
