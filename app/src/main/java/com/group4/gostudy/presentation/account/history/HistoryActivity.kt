package com.group4.gostudy.presentation.account.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.group4.gostudy.databinding.ActivityHistoryBinding
import com.group4.gostudy.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : AppCompatActivity() {

    private val binding: ActivityHistoryBinding by lazy {
        ActivityHistoryBinding.inflate(layoutInflater)
    }

    private val historyAdapter: HistoryListAdapter by lazy {
        HistoryListAdapter {}
    }

    private val historyViewModel: HistoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setClickListener()
        setHistoryRV()
        getData()
    }

    private fun getData() {
        historyViewModel.getHistory()
    }

    private fun setHistoryRV() {
        binding.rvHistoryList.apply {
            layoutManager = LinearLayoutManager(this@HistoryActivity, RecyclerView.VERTICAL, false)
            adapter = historyAdapter
        }
        setObserveHistoryData()
    }

    private fun setObserveHistoryData() {
        historyViewModel.history.observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    it.payload?.let {
                        historyAdapter.setData(it)
                    }
                }
            )
        }
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
