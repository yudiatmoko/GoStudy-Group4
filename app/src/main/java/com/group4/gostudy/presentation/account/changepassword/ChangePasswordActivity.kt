package com.group4.gostudy.presentation.account.changepassword

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.group4.gostudy.data.network.api.model.user.updatepassword.UpdatePasswordRequest
import com.group4.gostudy.databinding.ActivityChangePasswordBinding
import com.group4.gostudy.presentation.account.myprofile.MyProfileViewModel
import com.group4.gostudy.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordActivity : AppCompatActivity() {

    private val binding: ActivityChangePasswordBinding by lazy {
        ActivityChangePasswordBinding.inflate(layoutInflater)
    }

    private val viewModel: MyProfileViewModel by viewModel()

    private val changePasswordViewModel: ChangePasswordViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setClickListener()
        setForm()
        setDataOldPassword()
    }

    private fun setDataOldPassword() {
        viewModel.profile.observe(this) {
            it.proceedWhen(
                doOnSuccess = {
//                    binding.layoutForm.etOldPassword.setText(it.payload?.password)
                }
            )
        }
    }

    private fun updatePassword() {
        val newPassword = binding.layoutForm.etNewPassword.text.toString().trim()
        val oldPassword = binding.layoutForm.etOldPassword.text.toString().trim()
        val confirmPassword = binding.layoutForm.etConfirmNewPassword.text.toString().trim()

        changePasswordViewModel.updatePassword(
            UpdatePasswordRequest(
                newPassword = newPassword,
                oldPassword = oldPassword,
                confirmPassword = confirmPassword
            )
        )
    }

    private fun setForm() {
        binding.layoutForm.tilOldPassword.isVisible = true
        binding.layoutForm.tilNewPassword.isVisible = true
        binding.layoutForm.tilConfirmNewPassword.isVisible = true
    }

    private fun setClickListener() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.btnSavePassword.setOnClickListener {
            updatePassword()
        }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, ChangePasswordActivity::class.java)
            context.startActivity(intent)
        }
    }
}
