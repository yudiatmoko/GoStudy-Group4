package com.group4.gostudy.presentation.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.group4.gostudy.presentation.detail.about.AboutFragment
import com.group4.gostudy.presentation.detail.material.DetailCourseMaterialFragment

class AdapterViewPager(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            AboutFragment()
        } else {
            DetailCourseMaterialFragment()
        }
    }
}
