package com.group4.gostudy.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.group4.gostudy.databinding.FragmentHomeBinding
import com.group4.gostudy.model.CategoryProvider
import com.group4.gostudy.model.CourseProvider
import com.group4.gostudy.presentation.home.category.CategoryAdapter
import com.group4.gostudy.presentation.home.category.CourseCategoryAdapter
import com.group4.gostudy.presentation.home.popularcourse.PopularCourseAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter {}
    }
    private val courseCategoryAdapter: CourseCategoryAdapter by lazy {
        CourseCategoryAdapter {}
    }
    private val courseAdapter: PopularCourseAdapter by lazy {
        PopularCourseAdapter {}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
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

        setCategoryRV()
        setPopularCourseRV()
    }

    private fun setPopularCourseRV() {
        binding.rvCourse.apply {
            adapter = courseAdapter
            courseAdapter.setData(CourseProvider.getDummyData())
        }
    }

    private fun setCategoryRV() {
        binding.rvCategoryList.apply {
            adapter = categoryAdapter
            categoryAdapter.setData(CategoryProvider.getDummyData())
        }
        binding.rvCourseCategory.apply {
            adapter = courseCategoryAdapter
            courseCategoryAdapter.setData(CategoryProvider.getDummyData())
        }
    }
}
