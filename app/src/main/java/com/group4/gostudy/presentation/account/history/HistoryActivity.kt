package com.group4.gostudy.presentation.account.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.group4.gostudy.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private val binding: ActivityHistoryBinding by lazy {
        ActivityHistoryBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setClickListener()
    }

    private fun setClickListener() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, HistoryActivity::class.java)
            context.startActivity(intent)
        }
    }
}
