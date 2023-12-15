package com.group4.gostudy.presentation.detail.about

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.group4.gostudy.databinding.FragmentAboutBinding
import com.group4.gostudy.model.DetailCourse
import com.group4.gostudy.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding

    private val aboutViewModel: AboutViewModel by viewModel()

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
        showdataDetail(aboutViewModel.detail)
        setObserve()
    }

    private fun showdataDetail(item: DetailCourse?) {
        binding.tvAboutClass.text = item?.description
    }

    private fun setObserve() {
        aboutViewModel.getModule()
        aboutViewModel.modules.observe(viewLifecycleOwner) {
            it.proceedWhen {
            }
        }
    }

    companion object {
        const val EXTRA_PRODUCT = "EXTRA_PRODUCT"
        fun startActivity(context: Context, detail: DetailCourse?) {
            val intent = Intent(context, AboutFragment::class.java)
            intent.putExtra(EXTRA_PRODUCT, detail)
            context.startActivity(intent)
        }
    }
}
