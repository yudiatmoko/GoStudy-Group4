package com.group4.gostudy.presentation.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.group4.gostudy.databinding.FragmentAccountBinding
import com.group4.gostudy.databinding.LayoutLogoutDialogBinding
import com.group4.gostudy.presentation.account.changepassword.ChangePasswordActivity
import com.group4.gostudy.presentation.account.history.HistoryActivity
import com.group4.gostudy.presentation.account.myprofile.MyProfileActivity

class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding

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
        MyProfileActivity.startActivity(requireContext())
    }
    private fun navigateToHistory() {
        HistoryActivity.startActivity(requireContext())
    }
    private fun navigateToChangePassword() {
        ChangePasswordActivity.startActivity(requireContext())
    }
    private fun navigateToLogin() {
        requireActivity().run {
            startActivity(Intent(this, HistoryActivity::class.java))
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
