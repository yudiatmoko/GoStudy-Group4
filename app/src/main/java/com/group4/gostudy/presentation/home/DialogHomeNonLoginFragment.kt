package com.group4.gostudy.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.group4.gostudy.databinding.FragmentHomeNonLoginBinding

class DialogHomeNonLoginFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentHomeNonLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeNonLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginTemporary()
        exitTemporary()
    }

    private fun loginTemporary() {
        binding.btnLogin.setOnClickListener {
            dismiss()
        }
    }

    private fun exitTemporary() {
        binding.tbExitDialog.setOnClickListener {
            findNavController().popBackStack()
            dismiss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }
}
