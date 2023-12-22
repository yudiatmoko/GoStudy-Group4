package com.group4.gostudy.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.group4.gostudy.databinding.FragmentHomeNonLoginBinding
import com.group4.gostudy.presentation.login.LoginActivity

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
        navigateToLogin()
        exitButton()
    }

    private fun navigateToLogin() {
        binding.btnLogin.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun exitButton() {
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
