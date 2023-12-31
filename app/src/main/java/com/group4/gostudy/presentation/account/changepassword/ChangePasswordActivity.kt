package com.group4.gostudy.presentation.account.changepassword

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.group4.gostudy.data.network.api.model.user.updatepassword.UpdatePasswordRequest
import com.group4.gostudy.databinding.ActivityChangePasswordBinding
import com.group4.gostudy.utils.ApiException
import com.group4.gostudy.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordActivity : AppCompatActivity() {

    private val binding: ActivityChangePasswordBinding by lazy {
        ActivityChangePasswordBinding.inflate(layoutInflater)
    }

    private val changePasswordViewModel: ChangePasswordViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setClickListener()
        setForm()
    }

    private fun setUpdatePassword() {
        updatePassword()
        changePasswordViewModel.updatedPassword.observe(this) {
            it.proceedWhen(
                doOnError = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.animLoading.isVisible = false
                    binding.layoutState.llAnimError.isVisible = true
                    binding.layoutForm.root.isVisible = false
                    if (it.exception is ApiException) {
                        binding.layoutState.tvError.isVisible = true
                        binding.layoutState.tvError.text = it.exception.getParsedError()?.message
                        binding.btnRepeat.isVisible = true
                    }
                },
                doOnLoading = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.animLoading.isVisible = true
                    binding.layoutState.llAnimError.isVisible = false
                    binding.layoutForm.root.isVisible = false
                },
                doOnSuccess = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.animLoading.isVisible = false
                    binding.layoutState.llAnimError.isVisible = false
                    binding.layoutForm.root.isVisible = true
                    binding.layoutForm.etOldPassword.text?.clear()
                    binding.layoutForm.etNewPassword.text?.clear()
                    binding.layoutForm.etConfirmNewPassword.text?.clear()
                    Toast.makeText(
                        this,
                        it.payload,
                        Toast.LENGTH_LONG
                    ).show()
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
            setUpdatePassword()
        }
        binding.btnRepeat.setOnClickListener {
            finish()
            startActivity(this)
        }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, ChangePasswordActivity::class.java)
            context.startActivity(intent)
        }
    }
}
