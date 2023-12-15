package com.group4.gostudy.presentation.otp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.network.api.datasource.GoStudyApiDataSource
import com.group4.gostudy.data.network.api.model.otp.OtpResponse
<<<<<<< HEAD
import com.group4.gostudy.data.network.api.model.verify.VerifyRequest
=======
>>>>>>> origin/feature/feature_login
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

    fun verifyOtp(otp: String, token: String) {
        viewModelScope.launch {
            try {
                val verifyRequest = VerifyRequest(otp, token)

                val response = withContext(Dispatchers.IO) {
                    apiDataSource.verify(verifyRequest)
                }

                if (response.response.status == "success") {
                    // Jika status adalah "success," buat OtpResponse dan beri tahu hasil yang berhasil
                    val otpResponse = OtpResponse(response.response)
                    _otpResult.value = OtpResult.Success(otpResponse)
                } else {
                    // Jika status bukan "success," beri tahu hasil error
                    _otpResult.value = OtpResult.Error(Exception(response.response.message))
                }
            } catch (e: Exception) {
                // Handle any exceptions that may occur during OTP verification
                _otpResult.value = OtpResult.Error(e)
            }
        }
    }

    sealed class OtpResult {
        data class Success(val response: OtpResponse) : OtpResult()
        data class Error(val exception: Exception) : OtpResult()
    }
}
