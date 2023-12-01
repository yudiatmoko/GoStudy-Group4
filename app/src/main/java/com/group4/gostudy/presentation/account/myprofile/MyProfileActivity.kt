package com.group4.gostudy.presentation.account.myprofile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.group4.gostudy.databinding.ActivityMyProfileBinding
import com.group4.gostudy.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyProfileActivity : AppCompatActivity() {

    private val binding: ActivityMyProfileBinding by lazy {
        ActivityMyProfileBinding.inflate(layoutInflater)
    }

    private val profileViewModel: MyProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setClickListener()
        setForm()
        setDataProfile()
        getData()

        setLogin()
    }

    private fun setLogin() {
        profileViewModel.login()
    }

    private fun setDataProfile() {
        profileViewModel.profile.observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.layoutForm.etName.setText(it.payload?.name.orEmpty())
                    binding.layoutForm.etEmail.setText(it.payload?.email.orEmpty())
                    binding.layoutForm.etPhoneNumber.setText(it.payload?.phone.orEmpty())
                    binding.layoutForm.etCountry.setText(it.payload?.country.orEmpty())
                    binding.layoutForm.etCity.setText(it.payload?.city.orEmpty())
                }
            )
        }
    }

    private fun getData() {
        profileViewModel.getProfile()
    }

    private fun setForm() {
        binding.ivProfileImage.isVisible = true
        binding.layoutForm.tilName.isVisible = true
        binding.layoutForm.tilEmail.isVisible = true
        binding.layoutForm.tilPhoneNumber.isVisible = true
        binding.layoutForm.tilCountry.isVisible = true
        binding.layoutForm.tilCity.isVisible = true
    }

    private fun setClickListener() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.btnSave.setOnClickListener {
            updateProfile()
        }
    }

    private fun updateProfile() {
        val name = binding.layoutForm.etName.text.toString().trim()
        val email = binding.layoutForm.etEmail.text.toString().trim()
        val phone = binding.layoutForm.etPhoneNumber.text.toString().trim()
        val country = binding.layoutForm.etCountry.text.toString().trim()
        val city = binding.layoutForm.etCity.text.toString().trim()
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, MyProfileActivity::class.java)
            context.startActivity(intent)
        }
    }
}
