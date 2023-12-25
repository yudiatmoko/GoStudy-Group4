package com.group4.gostudy.presentation.account.historypayment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.repository.CourseRepository
import com.group4.gostudy.model.HistoryPayment
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class HistoryViewModel(
    private val repository: CourseRepository
) : ViewModel() {

    private val _historyPayment = MutableLiveData<ResultWrapper<List<HistoryPayment>>>()

    val historyPayment: LiveData<ResultWrapper<List<HistoryPayment>>>
        get() = _historyPayment

    fun getHistoryPayments() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getHistoryPayments().collect {
                _historyPayment.postValue(it)
            }
        }
    }
}
