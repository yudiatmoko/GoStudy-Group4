package com.group4.gostudy.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.load
import com.group4.gostudy.R
import com.group4.gostudy.databinding.FragmentHomeBinding
import com.group4.gostudy.model.Course
import com.group4.gostudy.presentation.account.myprofile.MyProfileActivity
import com.group4.gostudy.presentation.detail.DetailCourseActivity
import com.group4.gostudy.presentation.home.category.CategoryAdapter
import com.group4.gostudy.presentation.home.popularcourse.PopularCourseAdapter
import com.group4.gostudy.presentation.home.popularcourse.ViewMoreCourseActivity
import com.group4.gostudy.presentation.main.MainViewModel
import com.group4.gostudy.presentation.payment.PaymentActivity
import com.group4.gostudy.utils.ApiException
import com.group4.gostudy.utils.hideKeyboard
import com.group4.gostudy.utils.proceedWhen
import kotlinx.coroutines.launch
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
    private val courseAdapter: PopularCourseAdapter by lazy {
        PopularCourseAdapter { course: Course ->
            navigateToDetail(course)
        }
    }

    private fun navigateToDetail(courses: Course) {
        DetailCourseActivity.startActivity(requireContext(), courses)
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
        setClickListener()
    }

    private fun checkUserLoginAndLoadData() {
        lifecycleScope.launch {
            val userToken = mainViewModel.getUserToken()
            if (userToken.isNullOrBlank()) {
                navigateToNonLoginFragment()
            } else {
                navigateToMyProfile()
            }
        }
    }

    private val dialogFragment = DialogHomeNonLoginFragment()

    private fun navigateToNonLoginFragment() {
        dialogFragment.show(childFragmentManager, "DialogHomeNonLoginFragment")
    }

    private fun setClickListener() {
        binding.tvCourseViewMoreText.setOnClickListener {
            navigateToViewMore()
        }
        binding.ivProfileImage.setOnClickListener {
            checkUserLoginAndLoadData()
        }
    }

    private fun navigateToMyProfile() {
        activity.let {
            val intent = Intent(it, MyProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun navigateToViewMore() {
        activity.let {
            val intent = Intent(it, ViewMoreCourseActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setSearchFeature() {
        binding.svCourse.setOnCloseListener {
            hideKeyboard()
            homeViewModel.getCourse()
            false
        }
        binding.svCourse.setOnQueryTextListener(
            object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(
                    query: String?
                ): Boolean {
                    return if (!query.isNullOrEmpty()) {
                        homeViewModel.getCourse(search = query.trim())
                        false
                    } else {
                        homeViewModel.getCategory()
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

    private fun setHeader() {
        mainViewModel.getProfile()
        mainViewModel.profile.observe(
            viewLifecycleOwner
        ) {
            it.proceedWhen(
                doOnLoading = {
                    binding.sflHeader.isVisible = true
                    binding.sflHeader.startShimmer()
                    binding.llHeaderGreetings.isVisible = false
                    binding.ivProfileImage.isVisible = false
                },
                doOnSuccess = {
                    binding.sflHeader.stopShimmer()
                    binding.sflHeader.isVisible = false
                    binding.llHeaderGreetings.isVisible = true
                    binding.ivProfileImage.isVisible = true
                    binding.ivProfileImage.load(it.payload?.imageUrl) {
                        crossfade(true)
                    }
                    val firstName = it.payload?.let { it1 ->
                        mainViewModel.getFirstName(
                            it1
                        )
                    }
                    binding.tvGreetingText.text =
                        getString(
                            R.string.text_hello,
                            firstName
                        )
                },
                doOnError = {
                    binding.sflHeader.stopShimmer()
                    binding.sflHeader.isVisible = false
                    binding.llHeaderGreetings.isVisible = true
                    binding.ivProfileImage.isVisible = true
                },
                doOnEmpty = {
                    binding.sflHeader.stopShimmer()
                    binding.sflHeader.isVisible = false
                    binding.llHeaderGreetings.isVisible = true
                    binding.ivProfileImage.isVisible = true
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
                    binding.rvCourse.isVisible =
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
                },
                doOnEmpty = {
                    binding.layoutStateCategory.root.isVisible =
                        true
                    binding.layoutStateCategory.animLoading.isVisible =
                        false
                    binding.layoutStateCategory.tvError.isVisible =
                        true
                    binding.layoutStateCategory.llAnimError.isVisible =
                        true
                    binding.rvCategoryList.isVisible =
                        false
                    binding.layoutStateCategory.tvError.text =
                        getString(R.string.text_no_data)
                    if (it.exception is ApiException) {
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

    override fun onDestroyView() {
        binding.svCourse.setQuery(null, false)
        super.onDestroyView()
    }

    override fun onResume() {
        setHeader()
        super.onResume()
    }
}
