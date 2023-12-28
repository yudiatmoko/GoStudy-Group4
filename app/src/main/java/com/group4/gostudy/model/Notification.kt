package com.group4.gostudy.model

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

data class Notification(
    val category: String?,
    val createdAt: String?,
    val description: String?,
    val id: Int?,
    val title: String?,
    val updatedAt: String?
)

object NotificationProvider {
    fun getDummyData(): List<Notification> {
        return listOf(
//            Notification(UUID.randomUUID(), "Promosi", "Dapatkan Potongan 50% 1", "Syarat dan Ketentuan Berlaku 1"),
//            Notification(UUID.randomUUID(), "Promosi", "Dapatkan Potongan 50% 2", "Syarat dan Ketentuan Berlaku 2"),
//            Notification(UUID.randomUUID(), "Promosi", "Dapatkan Potongan 50% 3", "Syarat dan Ketentuan Berlaku 3"),
//            Notification(UUID.randomUUID(), "Notifikasi", "Ubah Password Behasil 4", "Password diubah pada Tanggal 23 November 2023, pukul 12.04 4"),
//            Notification(UUID.randomUUID(), "Notifikasi", "Ubah Password Behasil 5", "Password diubah pada Tanggal 23 November 2023, pukul 12.04 5")
        )
    }
}
