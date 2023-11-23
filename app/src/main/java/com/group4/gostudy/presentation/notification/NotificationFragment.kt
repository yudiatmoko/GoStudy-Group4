package com.group4.gostudy.presentation.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.group4.gostudy.databinding.FragmentNotificationBinding
import com.group4.gostudy.model.NotificationProvider

class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding
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
    }

    private fun setNotificationRV() {
        binding.rvNotificationList.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = notificationAdapter
            notificationAdapter.setData(NotificationProvider.getDummyData())
        }
    }
}
