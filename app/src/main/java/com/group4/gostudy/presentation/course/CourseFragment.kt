package com.group4.gostudy.presentation.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.group4.gostudy.databinding.FragmentCourseBinding
import com.group4.gostudy.model.CourseProvider
import com.group4.gostudy.model.TypeOfClassProvider
import com.group4.gostudy.presentation.course.course.CourseAdapter
import com.group4.gostudy.presentation.course.typeofclass.TypeOfClassAdapter
import com.group4.gostudy.presentation.home.DialogHomeNonLoginFragment

class CourseFragment : Fragment() {
    private lateinit var binding: FragmentCourseBinding
    private val typeOfClassAdapter: TypeOfClassAdapter by lazy {
        TypeOfClassAdapter {}
    }
    private val courseAdapter: CourseAdapter by lazy {
        CourseAdapter {}
    }
    private val dialogFragment = DialogHomeNonLoginFragment()
    private lateinit var searchView: SearchView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseBinding.inflate(inflater)
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
        setTypeOfClassRV()
        setCourseRV()
        navigateToNonLoginFragment()
        searchFeature()
    }

    private fun searchFeature() {
        searchView = binding.svCourse
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterCourses(newText)
                return true
            }

            private fun filterCourses(query: String?) {
                val originalData = CourseProvider.getDummyData()
                val filteredCourses = originalData.filter {
                    it.title.contains(query.orEmpty(), true)
                }
                courseAdapter.setData(filteredCourses)
            }
        })
    }

    private fun navigateToNonLoginFragment() {
        dialogFragment.show(childFragmentManager, "DialogHomeNonLoginFragment")
    }

    private fun setCourseRV() {
        binding.rvListOfClass.apply {
            adapter = courseAdapter
            courseAdapter.setData(CourseProvider.getDummyData())
        }
    }

    private fun setTypeOfClassRV() {
        binding.rvCatProgress.apply {
            adapter = typeOfClassAdapter
            typeOfClassAdapter.setData(TypeOfClassProvider.getDummyData())
        }
    }
}
