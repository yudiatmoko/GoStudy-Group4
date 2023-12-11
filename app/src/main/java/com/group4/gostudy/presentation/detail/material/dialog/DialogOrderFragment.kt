package com.group4.gostudy.presentation.detail.material.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.group4.gostudy.databinding.FragmentDialogOrderBinding
import com.group4.gostudy.presentation.home.HomeViewModel
import com.group4.gostudy.presentation.payment.PaymentActivity
import com.group4.gostudy.utils.ApiException
import com.group4.gostudy.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class DialogOrderFragment : Fragment() {
    private lateinit var binding: FragmentDialogOrderBinding
    private val dialogOrderViewModel: DialogOrderViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogOrderBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()
        observeData()
    }

    private fun observeData() {
        dialogOrderViewModel.getCourse()
        dialogOrderViewModel.courses.observe(
            viewLifecycleOwner
        ) {
            it.proceedWhen(
                doOnLoading = {
                    binding.layoutStateOrder.root.isVisible =
                        true
                    binding.layoutStateOrder.animLoading.isVisible =
                        true
                    binding.layoutStateOrder.llAnimError.isVisible =
                        false
                    binding.layoutContent.root.isVisible =
                        false
                    binding.btnOrder.isVisible = false
                },
                doOnSuccess = {
                    binding.layoutStateOrder.root.isVisible =
                        true
                    binding.layoutStateOrder.animLoading.isVisible =
                        false
                    binding.layoutStateOrder.llAnimError.isVisible =
                        false
                    binding.layoutContent.root.isVisible =
                        true
                    binding.btnOrder.isVisible = true
                },
                doOnError = {
                    binding.layoutStateOrder.root.isVisible =
                        true
                    binding.layoutStateOrder.animLoading.isVisible =
                        false
                    binding.layoutStateOrder.llAnimError.isVisible =
                        true
                    binding.layoutContent.root.isVisible =
                        false
                    binding.btnOrder.isVisible = false
                    if (it.exception is ApiException) {
                        binding.layoutStateOrder.tvError.isVisible =
                            true
                        binding.layoutStateOrder.tvError.text =
                            it.exception.getParsedError()?.message
                    }
                }
            )
        }
    }

    private fun setClickListener() {
        binding.btnOrder.setOnClickListener {
            navigateToPayment()
        }
    }

    private fun navigateToPayment() {
        val intent = Intent(requireContext(), PaymentActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }
}
