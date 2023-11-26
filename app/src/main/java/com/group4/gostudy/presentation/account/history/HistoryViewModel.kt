package com.group4.gostudy.presentation.account.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.repository.HistoryRepository
import com.group4.gostudy.model.History
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class HistoryViewModel(
    private val repository: HistoryRepository
) : ViewModel() {

    private val _history = MutableLiveData<ResultWrapper<List<History>>>()

    val history: LiveData<ResultWrapper<List<History>>>
        get() = _history

    fun getHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getHistories().collect {
                _history.postValue(it)
            }
        }
    }
}
