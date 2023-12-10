package com.group4.gostudy.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import coil.load
import com.group4.gostudy.R
import com.group4.gostudy.databinding.FragmentHomeBinding
import com.group4.gostudy.presentation.home.category.CategoryAdapter
import com.group4.gostudy.presentation.home.category.CourseCategoryAdapter
import com.group4.gostudy.presentation.home.popularcourse.PopularCourseAdapter
import com.group4.gostudy.presentation.main.MainViewModel
import com.group4.gostudy.utils.ApiException
import com.group4.gostudy.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModel()
    private val mainViewModel: MainViewModel by viewModel()
    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter {
            homeViewModel.getCourse(it.slug)
        }
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
        binding =
            FragmentHomeBinding.inflate(inflater)
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
        observeCategory()
        observeCourse()
        setHeader()
        setSearchFeature()
    }

    private fun setSearchFeature() {
        binding.svCourse.setOnQueryTextListener(
            object :
                SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (!query.isNullOrEmpty()) {
                        homeViewModel.getCourse(search = query)
                    }
                    return true
                }

                override fun onQueryTextChange(newQuery: String?): Boolean {
                    if (!newQuery.isNullOrEmpty()) {
                        homeViewModel.getCourse(search = newQuery)
                    } else {
                        homeViewModel.getCourse()
                    }
                    return true
                }
            }
        )

        binding.svCourse.setOnCloseListener {
            binding.svCourse.setQuery("", false)
            true
        }
    }

    private fun setHeader() {
        mainViewModel.getProfile()
        mainViewModel.profile.observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.ivProfileImage.load(it.payload?.imageUrl) {
                        crossfade(true)
                    }
                    val firstName = it.payload?.let { it1 ->
                        mainViewModel.getFirstName(it1)
                    }
                    binding.tvGreetingText.text = getString(
                        R.string.text_hello,
                        firstName
                    )
                }
            )
        }
    }

    private fun observeCourse() {
        homeViewModel.getCourse()
        homeViewModel.courses.observe(
            viewLifecycleOwner
        ) {
            it.proceedWhen(
                doOnLoading = {
                    binding.layoutStateCourse.root.isVisible =
                        true
                    binding.layoutStateCourse.animLoading.isVisible =
                        true
                    binding.layoutStateCourse.llAnimError.isVisible =
                        false
                    binding.rvCourse.isVisible =
                        false
                },
                doOnSuccess = {
                    binding.layoutStateCourse.root.isVisible =
                        true
                    binding.layoutStateCourse.animLoading.isVisible =
                        false
                    binding.layoutStateCourse.llAnimError.isVisible =
                        false
                    binding.rvCourse.isVisible =
                        true
                    it.payload?.let {
                        courseAdapter.setData(it)
                    }
                },
                doOnError = {
                    binding.layoutStateCourse.root.isVisible =
                        true
                    binding.layoutStateCourse.animLoading.isVisible =
                        false
                    binding.layoutStateCourse.llAnimError.isVisible =
                        true
                    binding.rvCourse.isVisible =
                        false
                    if (it.exception is ApiException) {
                        binding.layoutStateCourse.tvError.isVisible =
                            true
                        binding.layoutStateCourse.tvError.text =
                            it.exception.getParsedError()?.message
                    }
                }
            )
        }
    }

    private fun observeCategory() {
        homeViewModel.getCategory()
        homeViewModel.categories.observe(
            viewLifecycleOwner
        ) {
            it.proceedWhen(
                doOnLoading = {
                    binding.layoutStateCategory.root.isVisible =
                        true
                    binding.layoutStateCategory.animLoading.isVisible =
                        true
                    binding.layoutStateCategory.llAnimError.isVisible =
                        false
                    binding.rvCategoryList.isVisible =
                        false
                },
                doOnSuccess = {
                    binding.layoutStateCategory.root.isVisible =
                        true
                    binding.layoutStateCategory.animLoading.isVisible =
                        false
                    binding.layoutStateCategory.llAnimError.isVisible =
                        false
                    binding.rvCategoryList.isVisible =
                        true
                    it.payload?.let {
                        categoryAdapter.setData(it)
                    }
                    it.payload?.let {
                        courseCategoryAdapter.setData(
                            it
                        )
                    }
                },
                doOnError = {
                    binding.layoutStateCategory.root.isVisible =
                        true
                    binding.layoutStateCategory.animLoading.isVisible =
                        false
                    binding.layoutStateCategory.llAnimError.isVisible =
                        true
                    binding.rvCategoryList.isVisible =
                        false
                    if (it.exception is ApiException) {
                        binding.layoutStateCategory.tvError.isVisible =
                            true
                        binding.layoutStateCategory.tvError.text =
                            it.exception.getParsedError()?.message
                    }
                }
            )
        }
    }

    private fun setPopularCourseRV() {
        binding.rvCourse.apply {
            adapter = courseAdapter
        }
    }

    private fun setCategoryRV() {
        binding.rvCategoryList.apply {
            adapter = categoryAdapter
        }
    }
}
