package com.group4.gostudy.presentation.home.popularcourse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.group4.gostudy.R
import com.group4.gostudy.databinding.ActivityViewMoreCourseBinding
import com.group4.gostudy.model.Course
import com.group4.gostudy.presentation.detail.DetailCourseActivity
import com.group4.gostudy.presentation.home.HomeViewModel
import com.group4.gostudy.presentation.home.category.CategoryViewMoreAdapter
import com.group4.gostudy.utils.ApiException
import com.group4.gostudy.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewMoreCourseActivity : AppCompatActivity() {

    private val binding: ActivityViewMoreCourseBinding by lazy {
        ActivityViewMoreCourseBinding.inflate(layoutInflater)
    }

    private val homeViewModel: HomeViewModel by viewModel()
    private val categoryAdapter: CategoryViewMoreAdapter by lazy {
        CategoryViewMoreAdapter {
            homeViewModel.getCourse(it.slug)
        }
    }
    private val courseAdapter: PopularCourseViewMoreAdapter by lazy {
        PopularCourseViewMoreAdapter { course: Course ->
            navigateToDetail(course)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setCategoryRV()
        setCourseRV()
        observeCategory()
        observeCourse()
        setClickListener()
    }

    private fun navigateToDetail(courses: Course) {
        DetailCourseActivity.startActivity(this, courses)
    }

    private fun setClickListener() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setCourseRV() {
        binding.rvSeeAllPopularCourse.apply {
            adapter = courseAdapter
        }
    }

    private fun setCategoryRV() {
        binding.rvSeeAllCategory.apply {
            adapter = categoryAdapter
        }
    }

    private fun observeCourse() {
        homeViewModel.getCourse()
        homeViewModel.courses.observe(
            this
        ) {
            it.proceedWhen(
                doOnLoading = {
                    binding.layoutState.root.isVisible =
                        true
                    binding.layoutState.animLoading.isVisible =
                        true
                    binding.layoutState.llAnimError.isVisible =
                        false
                    binding.rvSeeAllPopularCourse.isVisible =
                        false
                },
                doOnSuccess = {
                    binding.layoutState.root.isVisible =
                        true
                    binding.layoutState.animLoading.isVisible =
                        false
                    binding.layoutState.llAnimError.isVisible =
                        false
                    binding.rvSeeAllPopularCourse.isVisible =
                        true
                    it.payload?.let {
                        courseAdapter.setData(it)
                    }
                },
                doOnError = {
                    binding.layoutState.root.isVisible =
                        true
                    binding.layoutState.animLoading.isVisible =
                        false
                    binding.layoutState.llAnimError.isVisible =
                        true
                    binding.rvSeeAllPopularCourse.isVisible =
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
                    binding.rvSeeAllPopularCourse.isVisible =
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

    private fun observeCategory() {
        homeViewModel.getCategory()
        homeViewModel.categories.observe(
            this
        ) {
            it.proceedWhen(
                doOnLoading = {
                    binding.layoutState.root.isVisible =
                        true
                    binding.layoutState.animLoading.isVisible =
                        true
                    binding.layoutState.llAnimError.isVisible =
                        false
                    binding.rvSeeAllCategory.isVisible =
                        false
                },
                doOnSuccess = {
                    binding.layoutState.root.isVisible =
                        true
                    binding.layoutState.animLoading.isVisible =
                        false
                    binding.layoutState.llAnimError.isVisible =
                        false
                    binding.rvSeeAllCategory.isVisible =
                        true
                    it.payload?.let {
                        categoryAdapter.setData(it)
                    }
                },
                doOnError = {
                    binding.layoutState.root.isVisible =
                        true
                    binding.layoutState.animLoading.isVisible =
                        false
                    binding.layoutState.llAnimError.isVisible =
                        true
                    binding.rvSeeAllCategory.isVisible =
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
                    binding.rvSeeAllCategory.isVisible =
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
}
