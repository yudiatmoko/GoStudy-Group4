package com.group4.gostudy.utils

import java.text.SimpleDateFormat

fun String?.convertDateTime(): String {
    return runCatching {
        requireNotNull(this) { "Tanggal Waktu Tidak Tersedia" }
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val outputFormatter = SimpleDateFormat("dd-MM-yy HH:mm")
        val date = inputFormatter.parse(this)
        outputFormatter.format(date)
    }.getOrElse { "Format Tanggal Waktu Tidak Valid" }
}
