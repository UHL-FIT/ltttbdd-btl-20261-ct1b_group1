package com.example.dattuadulich.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dattuadulich.data.remote.dto.TourModel
import com.example.dattuadulich.repository.PlaceRepository
import kotlinx.coroutines.delay
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

    private suspend fun getCities(): List<City> {
        delay(500)
        return listOf(
            City(
                name = "Hà Nội",
                description = "Thủ đô nghìn năm văn hiến với Hồ Gươm cổ kính.",
                imageUrl = "https://images.unsplash.com/photo-1599708153386-62e25078759c?q=80&w=1000&auto=format&fit=crop"
            ),
            City(
                name = "TP Hồ Chí Minh",
                description = "Thành phố năng động với Landmark 81 rực rỡ.",
                imageUrl = "https://images.unsplash.com/photo-1552733407-5d5c46c3bb3b?q=80&w=1000&auto=format&fit=crop"
            ),
            City(
                name = "Đà Nẵng",
                description = "Thành phố đáng sống với Cầu Rồng biểu tượng.",
                imageUrl = "https://images.unsplash.com/photo-1559592442-998818451124?q=80&w=1000&auto=format&fit=crop"
            ),
            City(
                name = "Hạ Long",
                description = "Kỳ quan thiên nhiên thế giới với Vịnh Hạ Long hùng vĩ.",
                imageUrl = "https://images.unsplash.com/photo-1524231757912-21f4fe3a7200?q=80&w=1000&auto=format&fit=crop"
            )
        )
    }

    fun fetchHomeData() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            try {
                val weather = repository.getWeather("Hanoi")

                val mockTours = listOf(
                    TourModel(1, "Hà Nội - Tour Ẩm Thực Đêm", "Hà Nội", "650.000đ", "https://images.unsplash.com/photo-1509042239860-f550ce710b93?auto=format&fit=crop&w=800&q=60", 4.9),
                    TourModel(2, "Sài Gòn - Ngắm Hoàng Hôn Sông Hàn", "TP HCM", "1.200.000đ", "https://images.unsplash.com/photo-1528127269322-539801943592?auto=format&fit=crop&w=800&q=60", 4.7),
                    TourModel(3, "Đà Nẵng - Bà Nà Hills Trọn Gói", "Đà Nẵng", "1.550.000đ", "https://images.unsplash.com/photo-1559592442-998818451124?auto=format&fit=crop&w=800&q=60", 4.8),
                    TourModel(4, "Hạ Long - Du Thuyền 5 Sao", "Quảng Ninh", "2.890.000đ", "https://images.unsplash.com/photo-1524231757912-21f4fe3a7200?auto=format&fit=crop&w=800&q=60", 5.0)
                )

                val cities = getCities()
                _uiState.value = HomeUiState.Success(weather, mockTours, cities)
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Lỗi tải dữ liệu")
            }
        }
    }
}
