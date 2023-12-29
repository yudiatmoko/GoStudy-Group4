package com.group4.gostudy.presentation.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.group4.gostudy.R
import com.group4.gostudy.databinding.FragmentNotificationBinding
import com.group4.gostudy.utils.ApiException
import com.group4.gostudy.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding
    private val notificationViewModel: NotificationViewModel by viewModel()
    private val notificationAdapter: NotificationListAdapter by lazy {
        NotificationListAdapter {}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(
            view,
            savedInstanceState
        )

        setNotificationRV()
        getData()
    }

    private fun setObserveNotificationData() {
        notificationViewModel.notifications.observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnLoading = {
                    binding.layoutState.root.isVisible =
                        true
                    binding.layoutState.animLoading.isVisible =
                        true
                    binding.layoutState.llAnimError.isVisible =
                        false
                    binding.rvNotificationList.isVisible =
                        false
                },
                doOnSuccess = {
                    binding.layoutState.root.isVisible =
                        true
                    binding.layoutState.animLoading.isVisible =
                        false
                    binding.layoutState.llAnimError.isVisible =
                        false
                    binding.rvNotificationList.isVisible =
                        true
                    it.payload?.let {
                        notificationAdapter.setData(it)
                    }
                },
                doOnError = {
                    binding.layoutState.root.isVisible =
                        true
                    binding.layoutState.animLoading.isVisible =
                        false
                    binding.layoutState.llAnimError.isVisible =
                        true
                    binding.rvNotificationList.isVisible =
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
                    binding.rvNotificationList.isVisible =
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
    private fun getData() {
        notificationViewModel.getNotification()
    }

    private fun setNotificationRV() {
        binding.rvNotificationList.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = notificationAdapter
        }
        setObserveNotificationData()
    }
}
