package com.group4.gostudy.presentation.account.myprofile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.group4.gostudy.data.network.api.model.user.updateuser.UpdateUserRequest
import com.group4.gostudy.databinding.ActivityMyProfileBinding
import com.group4.gostudy.presentation.main.MainViewModel
import com.group4.gostudy.utils.ApiException
import com.group4.gostudy.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyProfileActivity : AppCompatActivity() {

    private val binding: ActivityMyProfileBinding by lazy {
        ActivityMyProfileBinding.inflate(layoutInflater)
    }

    private val profileViewModel: MyProfileViewModel by viewModel()

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setClickListener()
        setForm()
        setDataProfile()
        getData()
    }

    private fun setLogin() {
        profileViewModel.login()
        profileViewModel.login.observe(this) {
            it.proceedWhen(
                doOnLoading = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.animLoading.isVisible = true
                    binding.layoutState.llAnimError.isVisible = false
                    binding.layoutForm.root.isVisible = false
                    binding.ivProfileImage.isVisible = false
                    binding.btnLogin.isVisible = false
                    binding.btnSave.isVisible = false
                },
                doOnSuccess = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.animLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = false
                    binding.layoutForm.root.isVisible = true
                    binding.ivProfileImage.isVisible = true
                    binding.btnLogin.isVisible = false
                    binding.btnSave.isVisible = true
                    finish()
                    startActivity(this)
                    it.payload?.let { token ->
                        mainViewModel.setUserToken(
                            token
                        )
                    }
                }
            )
        }
    }

    private fun setDataProfile() {
        profileViewModel.profile.observe(this) {
            it.proceedWhen(
                doOnLoading = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.animLoading.isVisible = true
                    binding.layoutState.llAnimError.isVisible = false
                    binding.layoutForm.root.isVisible = false
                    binding.ivProfileImage.isVisible = false
                    binding.btnLogin.isVisible = false
                    binding.btnSave.isVisible = false
                },
                doOnError = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.animLoading.isVisible = false
                    binding.layoutState.llAnimError.isVisible = true
                    binding.layoutForm.root.isVisible = false
                    binding.ivProfileImage.isVisible = false
                    binding.btnSave.isVisible = false
                    if (it.exception is ApiException) {
                        if (it.exception.httpCode == 401) {
                            binding.layoutState.tvError.isVisible = true
                            binding.layoutState.tvError.text = it.exception.getParsedError()?.message
                            binding.btnLogin.isVisible = true
                        } else {
                            binding.btnRepeat.isVisible = true
                            Toast.makeText(this@MyProfileActivity, it.exception.getParsedError()?.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                doOnSuccess = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.animLoading.isVisible = false
                    binding.layoutState.llAnimError.isVisible = false
                    binding.layoutForm.root.isVisible = true
                    binding.ivProfileImage.isVisible = true
                    binding.btnSave.isVisible = true
                    binding.btnLogin.isVisible = false
                    binding.layoutForm.etName.setText(it.payload?.name.orEmpty())
                    binding.layoutForm.etEmail.setText(it.payload?.email.orEmpty())
                    binding.layoutForm.etPhoneNumber.setText(it.payload?.phoneNumber.orEmpty())
                    binding.layoutForm.etCountry.setText(it.payload?.country.orEmpty())
                    binding.layoutForm.etCity.setText(it.payload?.city.orEmpty())
                    binding.ivProfileImage.load(it.payload?.imageUrl.orEmpty())
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
        binding.btnLogin.setOnClickListener {
            setLogin()
        }
    }

    private fun updateProfile() {
        val name = binding.layoutForm.etName.text.toString().trim()
        val phone = binding.layoutForm.etPhoneNumber.text.toString().trim()
        val country = binding.layoutForm.etCountry.text.toString().trim()
        val city = binding.layoutForm.etCity.text.toString().trim()

        profileViewModel.updateProfile(
            UpdateUserRequest(
                name = name,
                phoneNumber = phone,
                country = country,
                city = city,
                image = "https://raw.githubusercontent.com/yudiatmoko/Asset_Challenge_App_Ilham/main/img_all.webp"
            )
        )
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, MyProfileActivity::class.java)
            context.startActivity(intent)
        }
    }
}
