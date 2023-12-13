package com.group4.gostudy.presentation.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.group4.gostudy.databinding.ItemBottomFilterBinding

class DialogFragmentFilter : BottomSheetDialogFragment() {
    private lateinit var binding: ItemBottomFilterBinding

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
    }

    private fun exitTemporary() {
        binding.icCloseFilter.setOnClickListener {
            dismiss()
        }
    }
}
