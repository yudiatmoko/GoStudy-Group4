package com.group4.gostudy.presentation.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.group4.gostudy.databinding.FragmentAccountBinding
import com.group4.gostudy.presentation.account.history.HistoryActivity
import com.group4.gostudy.presentation.account.myprofile.MyProfileActivity

class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
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
        setClickListener()
    }

    private fun navigateToMyProfile() {
        MyProfileActivity.startActivity(requireContext())
    }
    private fun navigateToHistory() {
        HistoryActivity.startActivity(requireContext())
    }

    private fun setClickListener() {
        binding.cvMyProfileContain.setOnClickListener {
            navigateToMyProfile()
        }
        binding.cvHistoryContain.setOnClickListener {
            navigateToHistory()
        }
    }
}
