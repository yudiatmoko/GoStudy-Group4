package com.group4.gostudy.presentation.account.historypayment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.group4.gostudy.R
import com.group4.gostudy.databinding.ActivityHistoryBinding
import com.group4.gostudy.utils.ApiException
import com.group4.gostudy.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryPaymentActivity : AppCompatActivity() {

    private val binding: ActivityHistoryBinding by lazy {
        ActivityHistoryBinding.inflate(layoutInflater)
    }

    private val historyAdapter: HistoryPaymentListAdapter by lazy {
        HistoryPaymentListAdapter {}
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
        historyViewModel.getHistoryPayments()
    }

    private fun setHistoryRV() {
        binding.rvHistoryList.apply {
            layoutManager = LinearLayoutManager(this@HistoryPaymentActivity, RecyclerView.VERTICAL, false)
            adapter = historyAdapter
        }
        setObserveHistoryData()
    }

    private fun setObserveHistoryData() {
        historyViewModel.historyPayment.observe(this) {
            it.proceedWhen(
                doOnLoading = {
                    binding.layoutState.root.isVisible =
                        true
                    binding.layoutState.animLoading.isVisible =
                        true
                    binding.layoutState.llAnimError.isVisible =
                        false
                    binding.rvHistoryList.isVisible =
                        false
                },
                doOnSuccess = {
                    binding.layoutState.root.isVisible =
                        true
                    binding.layoutState.animLoading.isVisible =
                        false
                    binding.layoutState.llAnimError.isVisible =
                        false
                    binding.rvHistoryList.isVisible =
                        true
                    it.payload?.let {
                        historyAdapter.setData(it)
                    }
                },
                doOnError = {
                    binding.layoutState.root.isVisible =
                        true
                    binding.layoutState.animLoading.isVisible =
                        false
                    binding.layoutState.llAnimError.isVisible =
                        true
                    binding.rvHistoryList.isVisible =
                        false
                    if (it.exception is ApiException) {
                        binding.layoutState.tvError.isVisible =
                            true
                        binding.layoutState.tvError.text =
                            it.exception.getParsedError()?.message
                    }
                },
                doOnEmpty = {
                    binding.layoutState.root.isVisible =
                        true
                    binding.layoutState.animLoading.isVisible =
                        false
                    binding.layoutState.tvError.isVisible =
                        true
                    binding.layoutState.llAnimError.isVisible =
                        true
                    binding.rvHistoryList.isVisible =
                        false
                    binding.layoutState.tvError.text =
                        getString(R.string.text_no_data)
                    if (it.exception is ApiException) {
                        binding.layoutState.tvError.text =
                            it.exception.getParsedError()?.message
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
            val intent = Intent(context, HistoryPaymentActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onResume() {
        getData()
        setObserveHistoryData()
        super.onResume()
    }
}
