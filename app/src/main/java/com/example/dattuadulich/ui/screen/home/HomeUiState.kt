package com.example.dattuadulich.ui.screen.home

import com.example.dattuadulich.data.remote.dto.TourModel
import com.example.dattuadulich.data.remote.dto.WeatherResponse

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(
        val weather: WeatherResponse,
        val tours: List<TourModel> = emptyList()
    ) : HomeUiState
    data class Error(val message: String) : HomeUiState
}
