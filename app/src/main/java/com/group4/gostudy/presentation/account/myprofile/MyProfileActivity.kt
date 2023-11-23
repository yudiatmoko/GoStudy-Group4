package com.group4.gostudy.presentation.account.myprofile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.group4.gostudy.databinding.ActivityMyProfileBinding

class MyProfileActivity : AppCompatActivity() {

    private val binding: ActivityMyProfileBinding by lazy {
        ActivityMyProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setClickListener()
        setForm()
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
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, MyProfileActivity::class.java)
            context.startActivity(intent)
        }
    }
}
