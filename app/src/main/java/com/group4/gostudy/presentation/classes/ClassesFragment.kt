package com.group4.gostudy.presentation.classes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.group4.gostudy.R
import com.group4.gostudy.databinding.FragmentClassesBinding
import com.group4.gostudy.model.Course
import com.group4.gostudy.model.UserCourse
import com.group4.gostudy.presentation.classes.myclass.MyClassAdapter
import com.group4.gostudy.presentation.detail.DetailCourseActivity
import com.group4.gostudy.presentation.home.DialogHomeNonLoginFragment
import com.group4.gostudy.presentation.main.MainViewModel
import com.group4.gostudy.utils.ApiException
import com.group4.gostudy.utils.hideKeyboard
import com.group4.gostudy.utils.proceedWhen
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClassesFragment : Fragment() {
    private lateinit var binding: FragmentClassesBinding
    private val dialogFragment = DialogHomeNonLoginFragment()
    private val classesViewModel: ClassesViewModel by viewModel()
    private val mainViewModel: MainViewModel by viewModel()

    private fun navigateToNonLoginFragment() {
        dialogFragment.show(childFragmentManager, "DialogHomeNonLoginFragment")
    }

    private val myClassAdapter: MyClassAdapter by lazy {
        MyClassAdapter { userCourse: UserCourse ->
            navigateToDetail(userCourse.courseX)
        }
    }

    private fun navigateToDetail(courses: Course?) {
        DetailCourseActivity.startActivity(requireContext(), courses)
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
        checkUserLoginAndLoadData()
        setMyClassRv()
        setSearchFeature()
        setTypeButtons()
    }

    private fun setTypeButtons() {
        binding.tvAllText.setOnClickListener {
            classesViewModel.getUserCourses()
        }

        binding.tvProgressText.setOnClickListener {
            classesViewModel.getUserCourses(status = "in_progress".trim())
        }

        binding.tvDoneText.setOnClickListener {
            classesViewModel.getUserCourses(status = "selesai".trim())
        }
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

    private fun observeCourse() {
        classesViewModel.getUserCourses()
        classesViewModel.usercourses.observe(
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
                        myClassAdapter.setData(it)
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
        binding.svCourse.setOnCloseListener() {
            hideKeyboard()
            classesViewModel.getUserCourses()
            false
        }

        binding.svCourse.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (!query.isNullOrEmpty()) {
                    classesViewModel.getUserCourses(search = query)
                    false
                } else {
                    classesViewModel.getUserCourses()
                    false
                }
            }

            override fun onQueryTextChange(newQuery: String?): Boolean {
                return false
            }
        })
    }

    private fun setMyClassRv() {
        binding.rvListOfClass.apply {
            adapter = myClassAdapter
        }
    }
}
