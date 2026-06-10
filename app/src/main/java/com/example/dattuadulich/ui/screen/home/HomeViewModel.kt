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
                imageUrl = "https://images.unsplash.com/photo-1725550798518-f551648e4c10?w=1000"
            ),
            City(
                name = "TP Hồ Chí Minh",
                description = "Thành phố năng động với Landmark 81 rực rỡ.",
                imageUrl = "https://plus.unsplash.com/premium_photo-1697729938237-680e72596e15?w=1000"
            ),
            City(
                name = "Đà Nẵng",
                description = "Thành phố đáng sống với Cầu Rồng biểu tượng.",
                imageUrl = "https://images.unsplash.com/photo-1440694997168-8ae4033554c7?w=1000"
            ),
            City(
                name = "Đà Lạt",
                description = "Thành phố mộng mơ nổi tiếng với hồ Xuân Hương, đồi thông và những vườn hoa rực rỡ.",
                imageUrl = "https://samtenhills.vn/wp-content/uploads/2024/11/kinh-nghiem-du-lich-da-lat-1-minh.jpg?w=1000"
            ),
            City(
                name = "Hạ Long",
                description = "Kỳ quan thiên nhiên thế giới với Vịnh Hạ Long hùng vĩ.",
                imageUrl = "https://images.unsplash.com/photo-1764645859246-8c6db98330a7?w1000"
            )
        )
    }

    fun fetchHomeData() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            try {
                val weather = repository.getWeather("Hanoi")

                val mockTours = listOf(
                    TourModel(1, "Hà Nội - Tour Ẩm Thực Đêm", "Hà Nội", "650.000đ", "https://images.unsplash.com/photo-1509042239860-f550ce710b93?w=800&q=60", 4.9),
                    TourModel(2, "Sài Gòn - Ngắm Hoàng Hôn Sông Hàn", "TP HCM", "1.200.000đ", "https://images.unsplash.com/photo-1528127269322-539801943592?w=800&q=60", 4.7),
                    TourModel(3, "Đà Nẵng - Bà Nà Hills Trọn Gói", "Đà Nẵng", "1.550.000đ", "https://images.unsplash.com/photo-1677126578124-ec01051d0a5f?w=800&q=60", 4.8),
                    TourModel(4, "Hạ Long - Du Thuyền 5 Sao", "Quảng Ninh", "2.890.000đ", "https://thanhnien.mediacdn.vn/Uploaded/linhnt.qc/2021_07_08/namaste/namaste_1_FWXY.jpg?w=800&q=60", 5.0)
                )

                val cities = getCities()
                _uiState.value = HomeUiState.Success(weather, mockTours, cities)
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Lỗi tải dữ liệu")
            }
        }
    }
}
