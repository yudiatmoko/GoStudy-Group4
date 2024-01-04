package com.group4.gostudy.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.group4.gostudy.data.local.datastore.NonLoginMode
import com.group4.gostudy.databinding.FragmentHomeNonLoginBinding
import com.group4.gostudy.presentation.login.LoginActivity
import com.group4.gostudy.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DialogHomeNonLoginFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentHomeNonLoginBinding

    private val mainViewModel: MainViewModel by viewModel()

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
        setClickListener()
    }

    private fun setClickListener() {
        binding.btnLogin.setOnClickListener {
            navigateToLogin()
        }
        binding.tbExitDialog.setNavigationOnClickListener {
            exitButton()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(requireContext(), LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
        mainViewModel.setNonLogin(NonLoginMode.LOGOUT_FROM_GUEST.value)
    }

    private fun exitButton() {
        findNavController().popBackStack()
        dismiss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }
}
