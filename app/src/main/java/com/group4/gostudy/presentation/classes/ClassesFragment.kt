package com.group4.gostudy.presentation.classes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.group4.gostudy.databinding.FragmentClassesBinding
import com.group4.gostudy.model.CourseProvider
import com.group4.gostudy.model.ProgressCategoryProvider
import com.group4.gostudy.presentation.classes.myclass.MyClassAdapter
import com.group4.gostudy.presentation.classes.progresscategory.ProgressCategoryAdapter
import com.group4.gostudy.presentation.home.DialogHomeNonLoginFragment

class ClassesFragment : Fragment() {
    private lateinit var binding: FragmentClassesBinding
    private val progressCategoryAdapter: ProgressCategoryAdapter by lazy {
        ProgressCategoryAdapter {}
    }
    private val dialogFragment = DialogHomeNonLoginFragment()

    private fun navigateToNonLoginFragment() {
        dialogFragment.show(childFragmentManager, "DialogHomeNonLoginFragment")
    }

    private val myClassAdapter: MyClassAdapter by lazy {
        MyClassAdapter { _ ->
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClassesBinding.inflate(inflater)
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
        navigateToNonLoginFragment()
        setProgressCategoryRV()
        setMyClassRv()
    }

    private fun setMyClassRv() {
        binding.rvListOfClass.apply {
            adapter = myClassAdapter
            myClassAdapter.setData(CourseProvider.getDummyData())
        }
    }

    private fun setProgressCategoryRV() {
        binding.rvCatProgress.apply {
            adapter = progressCategoryAdapter
            progressCategoryAdapter.setData(ProgressCategoryProvider.getDummyData())
        }
    }
}
