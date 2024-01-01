package com.group4.gostudy.presentation.detail.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.group4.gostudy.databinding.FragmentAboutBinding
import com.group4.gostudy.model.Course
import com.group4.gostudy.presentation.detail.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding
    private val detailViewModel: DetailViewModel by viewModel { parametersOf(requireActivity().intent?.extras) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showData(detailViewModel.course)
        setClickListener()
    }

    private fun setClickListener() {
        binding.btTele.setOnClickListener {
            navigateToTelegram()
        }
    }

    private fun navigateToTelegram() {
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/+x9TdOOSfGwZlMWI1"))
        intent.setPackage("org.telegram.messenger")
        startActivity(intent)
    }

    private fun showData(course: Course?) {
        course?.let {
            binding.tvAboutClass.text = it.description.toString()
            binding.tvPurposeClass.text = it.benefits?.joinToString("\n")
        }
    }
}
