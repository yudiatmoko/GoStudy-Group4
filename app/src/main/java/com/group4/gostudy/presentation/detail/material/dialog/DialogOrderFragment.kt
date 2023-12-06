package com.group4.gostudy.presentation.detail.material.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.group4.gostudy.databinding.FragmentDialogOrderBinding
import com.group4.gostudy.presentation.payment.PaymentActivity

class DialogOrderFragment : Fragment() {
    private lateinit var binding: FragmentDialogOrderBinding

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
