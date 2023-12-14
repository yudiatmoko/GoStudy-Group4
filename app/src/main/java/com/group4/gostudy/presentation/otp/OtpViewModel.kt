package com.group4.gostudy.presentation.otp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.network.api.datasource.GoStudyApiDataSource
import com.group4.gostudy.data.network.api.model.otp.OtpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OtpViewModel(private val apiDataSource: GoStudyApiDataSource) : ViewModel() {

    private val _otpResult = MutableLiveData<OtpResult>()
    val otpResult: LiveData<OtpResult> get() = _otpResult

    fun resendOtp() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiDataSource.resendOtp()
                }
                _otpResult.value = OtpResult.Success(response)
            } catch (e: Exception) {
                _otpResult.value = OtpResult.Error(e)
            }
        }
    }

    sealed class OtpResult {
        data class Success(val response: OtpResponse) : OtpResult()
        data class Error(val exception: Exception) : OtpResult()
    }
}
