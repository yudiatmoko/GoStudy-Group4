package com.group4.gostudy.utils

import android.icu.text.NumberFormat
import android.icu.util.Currency
import kotlin.math.roundToInt

fun Double.toCurrencyFormat(): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 0
    format.currency = Currency.getInstance("IDR")
    return format.format(this.roundToInt())
}
