package com.group4.gostudy.presentation.payment.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.group4.gostudy.databinding.FragmentDialogSuccessBinding


class DialogSuccessFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentDialogSuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogSuccessBinding.inflate(inflater, container, false)
        return binding.root
    }
}
