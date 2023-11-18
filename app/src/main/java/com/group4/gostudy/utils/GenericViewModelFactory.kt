package com.group4.gostudy.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object GenericViewModelFactory {
    fun create(vm: ViewModel) = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = vm as T
    }
}
