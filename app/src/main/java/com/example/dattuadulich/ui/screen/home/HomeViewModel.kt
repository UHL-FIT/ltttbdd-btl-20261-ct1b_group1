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
                val cities = getCities()
                // Đã xóa bỏ chữ mockTours ở đây
                _uiState.value = HomeUiState.Success(cities)
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Lỗi tải dữ liệu")
            }
        }
    }
}
