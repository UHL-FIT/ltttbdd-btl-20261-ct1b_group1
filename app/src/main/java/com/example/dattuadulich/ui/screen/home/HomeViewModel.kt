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
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import org.json.JSONObject
import java.text.NumberFormat
import java.util.Locale
// [YÊU CẦU CƠ BẢN 2]: Thiết kế theo kiến trúc MVVM (ViewModel tách biệt khỏi UI)
class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PlaceRepository()// repository chứa logic
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchHomeData()
    }

    private suspend fun getCities(): List<City> {
        // 1. Đọc nội dung file bang_gia.json
        val jsonString = getApplication<Application>().assets.open("bang_gia.json").bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(jsonString)

        val cityList = mutableListOf<City>()

        // 2. Quét qua từng địa điểm
        for (cityName in jsonObject.keys()) {
            val cityData = jsonObject.getJSONObject(cityName)
            val imageUrl = cityData.getString("anhDiaDiem")
            val price = cityData.getDouble("giaTien")

            // Định dạng giá tiền (VD: 1.500.000 đ)
            val formattedPrice = NumberFormat.getNumberInstance(Locale.forLanguageTag("vi-VN")).format(price)

            // 3. Đóng gói vào danh sách
            cityList.add(
                City(
                    name = cityName,
                    description = "Giá tour tham khảo: $formattedPrice đ",
                    imageUrl = imageUrl
                )
            )
        }

        return cityList
    }

    fun fetchHomeData() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            try {
                // [YÊU CẦU NÂNG CAO A]: Dùng Retrofit + Coroutine gọi REST API thực
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
