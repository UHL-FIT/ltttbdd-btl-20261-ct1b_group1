package com.example.dattuadulich.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dattuadulich.data.remote.dto.TourModel
import com.example.dattuadulich.repository.PlaceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = PlaceRepository()
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchHomeData()
    }

    fun fetchHomeData() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            try {
                val weather = repository.getWeather("Hanoi")
                val mockTours = listOf(
                    TourModel(1, "Hạ Long – Kỳ quan thiên nhiên", "Quảng Ninh", "1.890.000đ", "https://i.ibb.co/v4S8L8Y/halong.jpg", 4.8),
                    TourModel(2, "Bãi Cháy – Sun World Hạ Long", "Quảng Ninh", "950.000đ", "https://i.ibb.co/mS6p0v3/baichay.jpg", 4.6),
                    TourModel(3, "Yên Tử – Chốn thiền linh thiêng", "Quảng Ninh", "850.000đ", "https://i.ibb.co/fN0mP2Z/yentu.jpg", 4.7),
                    TourModel(4, "Cô Tô – Biển xanh cát trắng", "Quảng Ninh", "2.200.000đ", "https://i.ibb.co/S7m0S3M/coto.jpg", 4.9),
                    TourModel(5, "Bình Liêu – Sống lưng khủng long", "Quảng Ninh", "1.750.000đ", "https://i.ibb.co/Xz9t6Xz/binhlieu.jpg", 4.5)
                )
                _uiState.value = HomeUiState.Success(weather, mockTours)
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}
