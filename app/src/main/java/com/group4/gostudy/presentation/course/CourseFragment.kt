package com.group4.gostudy.presentation.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.group4.gostudy.R
import com.group4.gostudy.databinding.FragmentCourseBinding
import com.group4.gostudy.model.Course
import com.group4.gostudy.presentation.course.course.CourseAdapter
import com.group4.gostudy.presentation.detail.DetailCourseActivity
import com.group4.gostudy.presentation.home.DialogHomeNonLoginFragment
import com.group4.gostudy.presentation.main.MainViewModel
import com.group4.gostudy.utils.ApiException
import com.group4.gostudy.utils.hideKeyboard
import com.group4.gostudy.utils.proceedWhen
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CourseFragment : Fragment(), DialogFragmentFilter.FilterListener {
    private lateinit var binding: FragmentCourseBinding
    private val courseAdapter: CourseAdapter by lazy {
        CourseAdapter { course: Course ->
            navigateToDetail(course)
        }
    }
    private val dialogFragment = DialogHomeNonLoginFragment()
    private val courseViewModel: CourseViewModel by viewModel()
    private val filterFragment = DialogFragmentFilter()
    private val selectedLevels = mutableListOf<String>()
    private val selectedCategories = mutableListOf<String>()
    private var createAt: Boolean = false
    private var promo: Boolean = false
    private val mainViewModel: MainViewModel by viewModel()
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
        setCourseRV()
        setSearchFeature()
        checkUserLoginAndLoadData()
        navigateToFilter()
        setupFilterButtons()
    }

    private fun checkUserLoginAndLoadData() {
        lifecycleScope.launch {
            val userToken = mainViewModel.getUserToken()
            if (userToken.isNullOrBlank()) {
                navigateToNonLoginFragment()
            } else {
                observeCourse()
            }
        }
    }

    private fun navigateToNonLoginFragment() {
        dialogFragment.show(childFragmentManager, "DialogHomeNonLoginFragment")
    }

    private fun navigateToDetail(courses: PopularCourse) {
        DetailCourseActivity.startActivity(requireContext(), courses)
    }

    private fun setupFilterButtons() {
        binding.tvAllText.setOnClickListener {
            courseViewModel.getCourse()
        }

        binding.tvFreeText.setOnClickListener {
            courseViewModel.getCourse(type = "Free".trim())
        }

        binding.tvPremiumText.setOnClickListener {
            courseViewModel.getCourse(type = "Premium".trim())
        }
    }

    private fun navigateToFilter() {
        binding.tvClassFilterText.setOnClickListener {
            filterFragment.setFilterListener(this@CourseFragment)
            filterFragment.show(childFragmentManager, "DialogFragmentFilter")
        }
    }

    private fun observeCourse() {
        courseViewModel.getCourse()
        courseViewModel.courses.observe(
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
                    binding.rvListOfClass.isVisible =
                        false
                },
                doOnSuccess = {
                    binding.layoutStateCourse.root.isVisible =
                        true
                    binding.layoutStateCourse.animLoading.isVisible =
                        false
                    binding.layoutStateCourse.llAnimError.isVisible =
                        false
                    binding.rvListOfClass.isVisible =
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
                    binding.rvListOfClass.isVisible =
                        false
                    if (it.exception is ApiException) {
                        binding.layoutStateCourse.tvError.isVisible =
                            true
                        binding.layoutStateCourse.tvError.text =
                            it.exception.getParsedError()?.message
                    }
                },
                doOnEmpty = {
                    binding.layoutStateCourse.root.isVisible =
                        true
                    binding.layoutStateCourse.animLoading.isVisible =
                        false
                    binding.layoutStateCourse.tvError.isVisible =
                        true
                    binding.layoutStateCourse.llAnimError.isVisible =
                        true
                    binding.rvListOfClass.isVisible =
                        false
                    binding.layoutStateCourse.tvError.text =
                        getString(R.string.text_no_data)
                    if (it.exception is ApiException) {
                        binding.layoutStateCourse.tvError.text =
                            it.exception.getParsedError()?.message
                    }
                }
            )
        }
    }

    private fun setSearchFeature() {
        binding.svCourse.setOnCloseListener {
            hideKeyboard()
            courseViewModel.getCourse()
            false
        }
        binding.svCourse.setOnQueryTextListener(
            object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(
                    query: String?
                ): Boolean {
                    return if (!query.isNullOrEmpty()) {
                        courseViewModel.getCourse(search = query.trim())
                        false
                    } else {
                        false
                    }
                }

                override fun onQueryTextChange(
                    newQuery: String?
                ): Boolean {
                    return false
                }
            }
        )
    }

    private fun setCourseRV() {
        binding.rvListOfClass.apply {
            adapter = courseAdapter
        }
    }

    override fun onFilterSelected(
        levels: List<String>?,
        categorySelected: List<String>?,
        createAt: Boolean?,
        promo: Boolean?
    ) {
        selectedLevels.clear()
        levels?.let { selectedLevels.addAll(it) }
        selectedCategories.clear()
        categorySelected?.let { selectedCategories.addAll(it) }
        this.createAt = createAt ?: false
        this.promo = promo ?: false
        applyFilter()
    }

    private fun applyFilter() {
        val level: List<String>? = when {
            selectedLevels.isEmpty() -> null
            else -> selectedLevels.toList()
        }
        val category: List<String>? = when {
            selectedCategories.isEmpty() -> null
            else -> selectedCategories.toList()
        }

        val createAtParam: Boolean? = if (createAt) createAt else null
        val promoParam: Boolean? = if (promo) promo else null

        courseViewModel.getCourse(
            levels = level,
            category = category,
            createAt = createAtParam,
            promoPercentage = promoParam
        )
    }
}
