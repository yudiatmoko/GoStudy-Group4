package com.group4.gostudy.presentation.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.group4.gostudy.R
import com.group4.gostudy.databinding.ItemBottomFilterBinding
import com.group4.gostudy.presentation.course.filter.FilterAdapter
import com.group4.gostudy.utils.ApiException
import com.group4.gostudy.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class DialogFragmentFilter : BottomSheetDialogFragment() {
    private lateinit var binding: ItemBottomFilterBinding
    private var filterListener: FilterListener? = null
    private val filterAdapter: FilterAdapter by lazy {
        FilterAdapter {}
    }
    private val courseViewModel: CourseViewModel by viewModel()

    interface FilterListener {
        fun onFilterSelected(
            levels: List<String>?,
            categorySelected: List<String>?,
            createAt: Boolean?,
            promo: Boolean?,
            rating: Boolean?
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemBottomFilterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exitTemporary()
        setButtonFilter()
        setFilterCategoryRV()
        observeCategory()
    }

    private fun observeCategory() {
        courseViewModel.getCategory()
        courseViewModel.categories.observe(
            viewLifecycleOwner
        ) {
            it.proceedWhen(
                doOnLoading = {
                    binding.layoutStateCourse.root.isVisible =
                        true
                    binding.layoutStateCourse.animLoading.isVisible =
                        true
                    binding.layoutStateCourse.llAnimError.isVisible =
                        false
                    binding.rvFilterCategory.isVisible =
                        false
                },
                doOnSuccess = {
                    binding.layoutStateCourse.root.isVisible =
                        true
                    binding.layoutStateCourse.animLoading.isVisible =
                        false
                    binding.layoutStateCourse.llAnimError.isVisible =
                        false
                    binding.rvFilterCategory.isVisible =
                        true
                    it.payload?.let { result ->
                        val categories = result
                        filterAdapter.setData(categories)
                        for (category in categories) {
                            val name = category.name
                        }
                    }
                },
                doOnError = {
                    binding.layoutStateCourse.root.isVisible =
                        true
                    binding.layoutStateCourse.animLoading.isVisible =
                        false
                    binding.layoutStateCourse.llAnimError.isVisible =
                        true
                    binding.rvFilterCategory.isVisible =
                        false
                    if (it.exception is ApiException) {
                        binding.layoutStateCourse.tvError.isVisible =
                            true
                        binding.layoutStateCourse.tvError.text =
                            it.exception.getParsedError()?.message
                    }
                },
                doOnEmpty = {
                    binding.layoutStateCourse.root.isVisible =
                        true
                    binding.layoutStateCourse.animLoading.isVisible =
                        false
                    binding.layoutStateCourse.tvError.isVisible =
                        true
                    binding.layoutStateCourse.llAnimError.isVisible =
                        true
                    binding.rvFilterCategory.isVisible =
                        false
                    binding.layoutStateCourse.tvError.text =
                        getString(R.string.text_no_data)
                    if (it.exception is ApiException) {
                        binding.layoutStateCourse.tvError.text =
                            it.exception.getParsedError()?.message
                    }
                }
            )
        }
    }

    private fun setFilterCategoryRV() {
        binding.rvFilterCategory.apply {
            adapter = filterAdapter
        }
    }

    private fun setButtonFilter() {
        binding.btnFilter.setOnClickListener {
            setCheckFilter()
        }
        binding.btnDeleteFilter.setOnClickListener {
            setDeleteFilter()
        }
    }

    private fun setDeleteFilter() {
        clearFilterCheckboxes()
        filterListener?.onFilterSelected(
            emptyList(),
            emptyList(),
            false,
            false,
            false
        )
    }

    private fun setCheckFilter() {
        val selectedLevels = mutableListOf<String>()
        val selectedCategories = mutableListOf<String>()
        var createAt = false
        var promo = false
        var rating = false

        with(binding) {
            if (cbBeginnerLevel.isChecked) selectedLevels.add("Beginner")
            if (cbIntermediateLevel.isChecked) selectedLevels.add("Intermediate")
            if (cbAdvancedLevel.isChecked) selectedLevels.add("Advanced")
            if (cbAllLevel.isChecked) selectedLevels.add("")
            if (cbPalingBaru.isChecked) {
                createAt = true
            }
            if (cbPromo.isChecked) {
                promo = true
            }
            if (cbPalingPopuler.isChecked) {
                rating = true
            }
            val categories = courseViewModel.categories.value?.payload.orEmpty()
            selectedCategories.addAll(categories.filter { it.isChecked }.map { it.name ?: "" })

            if (selectedLevels.isEmpty() && selectedCategories.isEmpty() && !createAt && !promo && !rating) {
                Toast.makeText(
                    requireContext(),
                    "Kamu belum memilih filter apapun",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                filterListener?.onFilterSelected(
                    selectedLevels,
                    selectedCategories,
                    createAt,
                    promo,
                    rating
                )
                dismiss()
            }
        }
    }

    private fun exitTemporary() {
        binding.icCloseFilter.setOnClickListener {
            dismiss()
        }
    }

    private fun clearFilterCheckboxes() {
        with(binding) {
            cbAllLevel.isChecked = false
            cbBeginnerLevel.isChecked = false
            cbIntermediateLevel.isChecked = false
            cbAdvancedLevel.isChecked = false
            cbPalingBaru.isChecked = false
            cbPromo.isChecked = false
            cbPalingPopuler.isChecked = false
        }
        courseViewModel.categories.value?.payload?.forEach { category ->
            category.isChecked = false
        }
        filterAdapter.notifyDataSetChanged()
    }

    fun setFilterListener(listener: FilterListener) {
        filterListener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }
}
