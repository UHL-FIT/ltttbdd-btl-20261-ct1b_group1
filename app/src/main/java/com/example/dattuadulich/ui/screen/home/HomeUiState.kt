package com.example.dattuadulich.ui.screen.home

import com.example.dattuadulich.data.remote.dto.TourModel
import com.example.dattuadulich.data.remote.dto.WeatherResponse

// 1. Tạo data class City trực tiếp trong HomeUiState (hoặc HomeViewModel)
data class City(
    val name: String,
    val description: String,
    val imageUrl: String
)

sealed class HomeUiState {
    object Loading : HomeUiState()

    data class Success(
        val cities: List<City> // Thêm list city vào Success state
    ) : HomeUiState()

    data class Error(
        val message: String
    ) : HomeUiState()
}