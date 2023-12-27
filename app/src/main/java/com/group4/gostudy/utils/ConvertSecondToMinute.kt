package com.group4.gostudy.utils

fun Int?.formatDurationToMinutes(): String {
    if (this == null || this <= 0) {
        return "0"
    }
    val minutes = this / 60 + if (this % 60 > 0) 1 else 0

    return if (minutes > 0) {
        "$minutes"
    } else {
        "Kurang dari 1 Menit"
    }
}
