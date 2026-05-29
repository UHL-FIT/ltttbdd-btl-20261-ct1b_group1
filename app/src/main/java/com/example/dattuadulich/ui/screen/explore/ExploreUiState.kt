package com.example.dattuadulich.ui.screen.explore

import com.example.dattuadulich.data.remote.dto.TourModel

data class ForecastItem(
    val day: String,
    val icon: String,
    val temp: String
)

data class WeatherData(
    val temp: String,
    val description: String,
    val humidity: String,
    val windSpeed: String,
    val icon: String,
    val forecast: List<ForecastItem>
)

data class TravelSuggestion(
    val cityName: String,
    val description: String,
    val reason: String,
    val imageUrl: String
)

data class ExploreUiState(
    val weather: WeatherData? = null,
    val suggestion: TravelSuggestion? = null,
    val tours: List<TourModel> = emptyList(),
    val searchQuery: String = "",
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
