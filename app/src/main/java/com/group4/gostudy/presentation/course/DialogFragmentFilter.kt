package com.group4.gostudy.presentation.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.group4.gostudy.databinding.ItemBottomFilterBinding

class DialogFragmentFilter : BottomSheetDialogFragment() {
    private lateinit var binding: ItemBottomFilterBinding
    private var filterListener: FilterListener? = null

    interface FilterListener {
        fun onFilterSelected(
            levels: List<String>?,
            categorySelected: List<String>?,
            createAt: Boolean?,
            promo: Boolean?
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
        val selectedLevels = mutableListOf<String>()
        val categorySelected = mutableListOf<String>()
        val createAt = false
        val promo = false
        with(binding) {
            cbAllLevel.isChecked = false
            cbBeginnerLevel.isChecked = false
            cbIntermediateLevel.isChecked = false
            cbAdvancedLevel.isChecked = false
            cbUiux.isChecked = false
            cbWebDev.isChecked = false
            cbBi.isChecked = false
            cbAndroidDevelopment.isChecked = false
            cbDs.isChecked = false
            cbPalingBaru.isChecked = false
            cbPromo.isChecked = false
            cbPalingPopuler.isChecked = false
        }
        filterListener?.onFilterSelected(
            emptyList(),
            emptyList(),
            createAt,
            promo
        )
    }

    private fun setCheckFilter() {
        val selectedLevels = mutableListOf<String>()
        val categorySelected = mutableListOf<String>()
        var createAt = false
        var promo = false

        with(binding) {
            if (cbBeginnerLevel.isChecked) selectedLevels.add("Beginner")
            if (cbIntermediateLevel.isChecked) selectedLevels.add("Intermediate")
            if (cbAdvancedLevel.isChecked) selectedLevels.add("Advanced")
            if (cbAllLevel.isChecked) selectedLevels.add("All")
            if (cbUiux.isChecked) categorySelected.add("UI/UX")
            if (cbWebDev.isChecked) categorySelected.add("Web Development")
            if (cbAndroidDevelopment.isChecked) categorySelected.add("Android Development")
            if (cbDs.isChecked) categorySelected.add("DataCourseById Science")
            if (cbBi.isChecked) categorySelected.add("Business Intelligence")
            if (cbPalingBaru.isChecked) {
                createAt = true
            }
            if (cbPalingBaru.isChecked) {
                createAt = true
            }

            if (selectedLevels.isEmpty() && categorySelected.isEmpty() && !createAt && !promo) {
                Toast.makeText(
                    requireContext(),
                    "Kamu belum memilih filter apapun",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                filterListener?.onFilterSelected(selectedLevels, categorySelected, createAt, promo)
                dismiss()
            }
        }
    }

    private fun exitTemporary() {
        binding.icCloseFilter.setOnClickListener {
            dismiss()
        }
    }

    fun setFilterListener(listener: FilterListener) {
        filterListener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }
}
