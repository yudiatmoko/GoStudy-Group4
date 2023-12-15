package com.group4.gostudy.presentation.otp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.network.api.model.otp.OtpResponse
import com.group4.gostudy.data.repository.UserRepository
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OtpViewModel(private val repository: UserRepository) : ViewModel() {

    private val _verify = MutableLiveData<ResultWrapper<String>>()
    private val _resendOtp = MutableLiveData<ResultWrapper<OtpResponse>>()

    val verify: LiveData<ResultWrapper<String>>
        get() = _verify

    val resendOtp: LiveData<ResultWrapper<OtpResponse>>
        get() = _resendOtp

    fun verifyResult(otp: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.verify(otp).collect {
                _verify.postValue(it)
            }
        }
    }

    fun resendOtp() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.resendOtp().collect {
                _resendOtp.postValue(it)
            }
        }
    }
}
