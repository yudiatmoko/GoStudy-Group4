package com.group4.gostudy.presentation.detail.about

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

    private val aboutViewModel: AboutViewModel by viewModel()
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
    }

    private fun showData(course: Course?) {
        course?.let {
            binding.tvAboutClass.text = it.description.toString()
            binding.tvPurposeClass.text = it.benefits?.joinToString("\n")
        }
    }

/*    private fun observeChapter() {
        detailViewModel.desc?.let {
            AboutViewModel.
        }
        detailViewModel.course.(){
            it.proceedWhen(
                doOnLoading = {
                    binding.layoutStateAbout.root.isVisible =
                        true
                    binding.layoutStateAbout.animLoading.isVisible =
                        true
                    binding.layoutStateAbout.llAnimError.isVisible =
                        false
                    binding.clAbout.isVisible =
                        false
                },
                doOnSuccess = {
                    binding.layoutStateAbout.root.isVisible =
                        true
                    binding.layoutStateAbout.animLoading.isVisible =
                        false
                    binding.layoutStateAbout.llAnimError.isVisible =
                        false
                    binding.clAbout.isVisible = true
                    showData(detailViewModel.course)

                },
                doOnError = {
                    binding.layoutStateAbout.root.isVisible =
                        true
                    binding.layoutStateAbout.animLoading.isVisible =
                        false
                    binding.layoutStateAbout.llAnimError.isVisible =
                        true
                    binding.clAbout.isVisible =
                        false
                    if (it.exception is Exception) {
                        binding.layoutStateAbout.tvError.isVisible =
                            true
                        binding.layoutStateAbout.tvError.text =
                            it.exception.getParsedError()?.message
                    }
                }
            )
        }
    }*/
}

    /*companion object {
        const val EXTRA_PRODUCT = "EXTRA_PRODUCT"
        fun startActivity(context: Context, detail: AboutFragment?) {
            val intent = Intent(context, AboutFragment::class.java)
            intent.putExtra(EXTRA_PRODUCT, detail)
            context.startActivity(intent)
        }
    }
 }
*/
