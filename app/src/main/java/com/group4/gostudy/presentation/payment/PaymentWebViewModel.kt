package com.group4.gostudy.presentation.payment

import android.os.Bundle
import androidx.lifecycle.ViewModel

class PaymentWebViewModel(
    val extras: Bundle?
) : ViewModel() {
    val url = extras?.getString("URL")
    val idCourse = extras?.getInt("ID")
}
