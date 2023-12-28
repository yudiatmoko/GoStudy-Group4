package com.group4.gostudy.presentation.detail.material.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.group4.gostudy.databinding.FragmentDialogOrderBinding
import com.group4.gostudy.model.Course
import com.group4.gostudy.presentation.payment.PaymentActivity
import com.group4.gostudy.utils.ApiException
import com.group4.gostudy.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DialogOrderFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentDialogOrderBinding
    private val dialogOrderViewModel: DialogOrderViewModel by viewModel { parametersOf(requireActivity().intent?.extras) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        observeData()
    }

    private fun observeData() {
        dialogOrderViewModel.idCourse?.let {
            dialogOrderViewModel.getCourseById(it)
        }
        dialogOrderViewModel.courses.observe(
            viewLifecycleOwner
        ) {
            it.proceedWhen(
                doOnLoading = {
                    binding.layoutStateDialog.root.isVisible =
                        true
                    binding.layoutStateDialog.animLoading.isVisible =
                        true
                    binding.layoutStateDialog.llAnimError.isVisible =
                        false
                    binding.layoutContent.root.isVisible =
                        false
                    binding.btnOrder.isVisible = false
                },
                doOnSuccess = {
                    binding.layoutStateDialog.root.isVisible =
                        true
                    binding.layoutStateDialog.animLoading.isVisible =
                        false
                    binding.layoutStateDialog.llAnimError.isVisible =
                        false
                    binding.layoutContent.root.isVisible =
                        true
                    binding.btnOrder.isVisible = true
                    it.payload?.let {
                        setData(it)
                    }
                },
                doOnError = {
                    binding.layoutStateDialog.root.isVisible =
                        true
                    binding.layoutStateDialog.animLoading.isVisible =
                        false
                    binding.layoutStateDialog.llAnimError.isVisible =
                        true
                    binding.layoutContent.root.isVisible =
                        false
                    binding.btnOrder.isVisible = false
                    if (it.exception is ApiException) {
                        binding.layoutStateDialog.tvError.isVisible =
                            true
                        binding.layoutStateDialog.tvError.text =
                            it.exception.getParsedError()?.message
                    }
                }
            )
        }
    }

    private fun setData(item: Course) {
        item.let {
            binding.layoutContent.ivCourseImg.load(item.imageUrl)
            binding.layoutContent.tvCategoryName.text = item.category?.name
            binding.layoutContent.tvCourseTitle.text = item.name
            binding.layoutContent.tvInstructorName.text = String.format("by %s", item.courseBy)
            binding.layoutContent.tvDuration.text =
                String.format("%.0f Menit", item.totalDuration?.toDouble())
            binding.layoutContent.tvPrice.text =
                String.format("Beli Rp. %.0f", item.price?.toDouble())
            binding.layoutContent.tvLevel.text = item.level
            binding.layoutContent.tvRating.text = ""
            binding.layoutContent.tvModule.text =
                String.format("%.0f Modul", item.totalModule?.toDouble())
        }
    }

    private fun setClickListeners() {
        binding.btnOrder.setOnClickListener {
            navigateToPayment(detailViewModel.course)
        }
        binding.tbExitDialog.setOnClickListener {
            dismiss()
        }
    }

    private fun navigateToPayment(course: Course?) {
        PaymentActivity.startActivity(requireContext(), course)
    }
}
