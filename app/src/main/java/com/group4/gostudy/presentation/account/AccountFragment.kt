package com.group4.gostudy.presentation.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.group4.gostudy.databinding.FragmentAccountBinding
import com.group4.gostudy.databinding.LayoutLogoutDialogBinding
import com.group4.gostudy.presentation.account.changepassword.ChangePasswordActivity
import com.group4.gostudy.presentation.account.historypayment.HistoryPaymentActivity
import com.group4.gostudy.presentation.account.myprofile.MyProfileActivity
import com.group4.gostudy.presentation.home.DialogHomeNonLoginFragment
import com.group4.gostudy.presentation.login.LoginActivity
import com.group4.gostudy.presentation.main.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(
            view,
            savedInstanceState
        )
        setClickListener()
    }

    private fun navigateToMyProfile() {
        lifecycleScope.launch {
            val userToken = mainViewModel.getUserToken()
            if (userToken.isNullOrBlank()) {
                navigateToNonLoginFragment()
            } else {
                MyProfileActivity.startActivity(requireContext())
            }
        }
    }
    private fun navigateToHistory() {
        lifecycleScope.launch {
            val userToken = mainViewModel.getUserToken()
            if (userToken.isNullOrBlank()) {
                navigateToNonLoginFragment()
            } else {
                HistoryPaymentActivity.startActivity(requireContext())
            }
        }
    }
    private fun navigateToChangePassword() {
        lifecycleScope.launch {
            val userToken = mainViewModel.getUserToken()
            if (userToken.isNullOrBlank()) {
                navigateToNonLoginFragment()
            } else {
                ChangePasswordActivity.startActivity(requireContext())
            }
        }
    }

    private val dialogFragment = DialogHomeNonLoginFragment()

    private fun navigateToNonLoginFragment() {
        dialogFragment.show(childFragmentManager, "DialogHomeNonLoginFragment")
    }

    private fun navigateToLogin() {
        requireActivity().run {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun logoutDialog() {
        var alertDialog: AlertDialog? = null
        val builder = AlertDialog.Builder(requireContext())
        val view = LayoutLogoutDialogBinding.inflate(layoutInflater)
        builder.setView(view.root)

        view.confirmNoLogoutButton.setOnClickListener {
            alertDialog?.dismiss()
        }
        view.confirmYesLogoutButton.setOnClickListener {
            alertDialog?.dismiss()
            navigateToLogin()
            mainViewModel.removeUserToken()
        }

        alertDialog = builder.create()
        alertDialog.show()
    }

    private fun setClickListener() {
        binding.cvMyProfileContain.setOnClickListener {
            navigateToMyProfile()
        }
        binding.cvHistoryContain.setOnClickListener {
            navigateToHistory()
        }
        binding.cvChangePasswordContain.setOnClickListener {
            navigateToChangePassword()
        }
        binding.cvLogoutContain.setOnClickListener {
            logoutDialog()
        }
    }
}
