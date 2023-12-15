package com.group4.gostudy.presentation.detail.about

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.repository.DetailRepository
import com.group4.gostudy.model.DetailCourse
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AboutViewModel(
    private val extras: Bundle?,
    private val detailRepository: DetailRepository
) : ViewModel() {
    val detail = extras?.getParcelable<DetailCourse>(AboutFragment.EXTRA_PRODUCT)

    private val _module = MutableLiveData<ResultWrapper<List<DetailCourse>>>()

    val modules: LiveData<ResultWrapper<List<DetailCourse>>>
        get() = _module

    fun getModule() {
        viewModelScope.launch(Dispatchers.IO) {
            detailRepository.getModule().collect {
                _module.postValue(it)
            }
        }
    }
}
