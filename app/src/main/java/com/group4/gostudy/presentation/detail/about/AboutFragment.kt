package com.group4.gostudy.presentation.detail.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.group4.gostudy.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding

    //  private val viewModel: AboutViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater)
        return binding.root
    }

 /*   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserveAbout()
        showdataDetail()
    }

    private fun showdataDetail() {
        binding.tvAboutClass.text = viewModel.
    }

    private fun setObserveAbout() {
        viewModel.getModule()
        viewModel.modules.observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnLoading = {
                    binding.layoutStateCourse.root.isVisible = true
                    binding.layoutStateCourse.animLoading.isVisible = true
                    binding.layoutStateCourse.llAnimError.isVisible = false
                    binding.tvAboutClass.isVisible = false
                },
                doOnSuccess = {
                    binding.layoutStateCourse.root.isVisible = true
                    binding.layoutStateCourse.animLoading.isVisible = false
                    binding.layoutStateCourse.llAnimError.isVisible = false
                    binding.tvAboutClass.isVisible = true
                },
                doOnError = {
                    binding.layoutStateCourse.root.isVisible = true
                    binding.layoutStateCourse.animLoading.isVisible = false
                    binding.layoutStateCourse.llAnimError.isVisible = true
                    binding.tvAboutClass.isVisible = false
                    if (it.exception is ApiException) {
                        binding.layoutStateCourse.tvError.isVisible =
                            true
                        binding.layoutStateCourse.tvError.text =
                            it.exception.getParsedError()?.message
                    }
                },
                doOnEmpty = {
                    binding.layoutStateCourse.root.isVisible = true
                    binding.layoutStateCourse.animLoading.isVisible = false
                    binding.layoutStateCourse.tvError.isVisible = true
                    binding.layoutStateCourse.llAnimError.isVisible = true
                    binding.tvAboutClass.isVisible = false
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

    companion object {
        const val EXTRA_PRODUCT = "EXTRA_PRODUCT"
        fun startActivity(context: Context, detail: DetailCourse?) {
            val intent = Intent(context, AboutFragment::class.java)
            intent.putExtra(EXTRA_PRODUCT, detail)
            context.startActivity(intent)
        }
    }*/
}
