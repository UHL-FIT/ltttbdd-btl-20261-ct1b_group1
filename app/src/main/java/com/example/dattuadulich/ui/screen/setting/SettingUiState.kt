package com.example.dattuadulich.ui.screen.setting

data class SettingUiState(
    // 1. Thông tin cá nhân
    val userName: String = "Người dùng",
    val email: String = "thanhvien@nhom.com",
    val avatarUrl: String? = null,
    val phoneNumber: String = "",

    // 2. Trạng thái các nút gạt (Toggles)
    val notifyPromo: Boolean = true,
    val notifyReminder: Boolean = true,
    val notifyWeather: Boolean = false,
    val isDarkMode: Boolean = false,
    val isLocationServicesEnabled: Boolean = true,

    // 3. Các lựa chọn
    val selectedLanguage: String = "Tiếng Việt",
    val currency: String = "VND",

    // 4. Trạng thái hệ thống
    val isLoading: Boolean = false,
    val errorMessage: String? = null,

    // 5. Thông tin phiên bản
    val appVersion: String = "1.0.0"
)
