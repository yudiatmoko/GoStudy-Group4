package com.group4.gostudy.presentation.detail.material.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load
import com.group4.gostudy.databinding.FragmentDialogOrderBinding
import com.group4.gostudy.model.PopularCourse
import com.group4.gostudy.presentation.payment.PaymentActivity
import com.group4.gostudy.utils.ApiException
import com.group4.gostudy.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class DialogOrderFragment : Fragment() {
    private lateinit var binding: FragmentDialogOrderBinding
    private val dialogOrderViewModel: DialogOrderViewModel by viewModel()


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
        dialogOrderViewModel.getCourse()
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

    private fun setData(item: List<PopularCourse>) {
        if (item.isNotEmpty()) {
            val courses = item[0]
            binding.layoutContent.ivCourseImg.load(courses.imageUrl)
            binding.layoutContent.tvCategoryName.text = courses.category?.name
            binding.layoutContent.tvCourseTitle.text = courses.name
            binding.layoutContent.tvInstructorName.text = String.format("by %s", courses.courseBy)
            binding.layoutContent.tvDuration.text =
                String.format("%.0f Menit", courses.totalDuration?.toDouble())
            binding.layoutContent.tvPrice.text =
                String.format("Beli Rp. %.0f", courses.price?.toDouble())
            binding.layoutContent.tvLevel.text = courses.level
            binding.layoutContent.tvRating.text = ""
            binding.layoutContent.tvModule.text =
                String.format("%.0f Modul", courses.totalModule?.toDouble())
        }
    }


    private fun setClickListeners() {
        binding.btnOrder.setOnClickListener {
            navigateToPayment()
        }
        binding.toolbar.setOnClickListener {
            findNavController().popBackStack()
            //dismiss()
        }
    }

    private fun navigateToPayment() {
        val intent = Intent(requireContext(), PaymentActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }
}
