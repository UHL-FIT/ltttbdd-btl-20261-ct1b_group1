package com.example.dattuadulich.ui.screen.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dattuadulich.data.remote.dto.TourModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
// viewmodel chứa logic
class ExploreViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ExploreUiState()) // state chứa dữ liệu hiện tại
    val uiState: StateFlow<ExploreUiState> = _uiState.asStateFlow()

    init {
        fetchTours()
    }

    private fun fetchTours() {
        val mockTours = listOf(
            TourModel(1, "Hạ Long – Kỳ quan thiên nhiên", "Quảng Ninh", "1.890.000đ", "https://encrypted-tbn0.gstatic.com/licensed-image?q=tbn:ANd9GcSOugvG8FnC9BAPlFYdJMbVwKUitABzCf_ViPo91cSqrao4F8BIFUxBsTyEqOTIRDVMdQ1jDU81PG3XYxM1esebWVE&s=19", 4.8),
            TourModel(2, "Bãi Cháy – Sun World Hạ Long", "Quảng Ninh", "950.000đ", "https://cdn3.ivivu.com/2025/06/Sun-World-Ha-Long-ivivu-7-1.jpg", 4.6),
            TourModel(3, "Yên Tử – Chốn thiền linh thiêng", "Quảng Ninh", "850.000đ", "https://static.vinwonders.com/production/2025/03/chua-dong-yen-tu.jpg", 4.7)
        )
        _uiState.value = _uiState.value.copy(tours = mockTours)
    }

    fun onSearchQueryChange(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }

    fun searchWeather() {
        val city = _uiState.value.searchQuery.trim()
        if (city.isEmpty()) {
            fetchTours() // Reset to default tours if empty
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            delay(800) 
            val weatherData = getWeather(city)
            if (weatherData != null) {
                val suggestion = getSuggestion(weatherData)
                // Filter tours based on searched city
                val filteredTours = getAllMockTours().filter { 
                    it.location.contains(city, ignoreCase = true) || city.contains(it.location, ignoreCase = true)
                }
                
                _uiState.value = _uiState.value.copy(
                    weather = weatherData,
                    suggestion = suggestion,
                    tours = if (filteredTours.isNotEmpty()) filteredTours else getAllMockTours().take(3),
                    isLoading = false
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    weather = null,
                    suggestion = null,
                    errorMessage = "Không tìm thấy tỉnh thành",
                    isLoading = false
                )
            }
        }
    }

    private fun getAllMockTours(): List<TourModel> {
        return listOf(
            TourModel(1, "Hà Nội - Tour Ẩm Thực Đêm", "Hà Nội", "650.000đ", "https://images.unsplash.com/photo-1509042239860-f550ce710b93?w=800", 4.9),
            TourModel(2, "Hà Nội - Khám phá Phố Cổ", "Hà Nội", "450.000đ", "https://images.unsplash.com/photo-1679562078540-09ae866ef4bf?w=800", 4.8),
            TourModel(3, "Đà Nẵng - Bà Nà Hills Trọn Gói", "Đà Nẵng", "1.550.000đ", "https://images.unsplash.com/photo-1677126578124-ec01051d0a5f?w=800", 4.8),
            TourModel(4, "Đà Nẵng - Ngắm Cầu Rồng & Chợ Đêm", "Đà Nẵng", "800.000đ", "https://images.unsplash.com/photo-1603054561323-09f79d5df183??w=800", 4.7),
            TourModel(5, "Sài Gòn - City Tour & Bitexco", "TP HCM", "1.200.000đ", "https://media.tacdn.com/media/attractions-splice-spp-674x446/06/67/aa/39.jpg?w=800", 4.7),
            TourModel(6, "Sài Gòn - Thưởng Thức Cơm Tấm", "TP HCM", "350.000đ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQxACoDxIv9D11wMpioqpJa2Zx8Bq6p8nYeiQ&s&w=800", 4.6),
            TourModel(7, "Hạ Long - Du Thuyền 5 Sao", "Quảng Ninh", "2.890.000đ", "https://thanhnien.mediacdn.vn/Uploaded/linhnt.qc/2021_07_08/namaste/namaste_1_FWXY.jpg?w=800", 5.0),
            TourModel(8, "Bãi Cháy - Sun World Hạ Long", "Quảng Ninh", "950.000đ", "https://cdn3.ivivu.com/2025/06/Sun-World-Ha-Long-ivivu-7-1.jpg?w=800", 4.6),
            TourModel(9, "Yên Tử - Chốn thiền linh thiêng", "Quảng Ninh", "850.000đ", "https://static.vinwonders.com/production/2025/03/chua-dong-yen-tu.jpg?w=800", 4.7),
            TourModel(10, "Đà Lạt - Check-in Thung Lũng Tình Yêu", "Đà Lạt", "1.100.000đ", "https://static.vinwonders.com/production/thung-lung-tinh-yeu-5.jpg?w=800", 4.9)
        )
    }

    private suspend fun getWeather(city: String): WeatherData? {
        val normalizedCity = city.lowercase()
        return when {
            normalizedCity.contains("hà nội") -> WeatherData(
                temp = "28",
                description = "Trời nhiều mây",
                humidity = "65",
                windSpeed = "12.5",
                icon = "04d",
                forecast = generateForecast("Trời mát")
            )
            normalizedCity.contains("đà nẵng") || normalizedCity.contains("hội an") -> WeatherData(
                temp = "32",
                description = "Trời nắng đẹp",
                humidity = "60",
                windSpeed = "15.2",
                icon = "01d",
                forecast = generateForecast("Nắng")
            )
            normalizedCity.contains("hồ chí minh") || normalizedCity.contains("sài gòn") -> WeatherData(
                temp = "34",
                description = "Nắng nóng",
                humidity = "55",
                windSpeed = "10.0",
                icon = "01d",
                forecast = generateForecast("Nắng nóng")
            )
            normalizedCity.contains("quảng ninh") || normalizedCity.contains("hạ long") || normalizedCity.contains("bãi cháy") || normalizedCity.contains("yên tử") || normalizedCity.contains("cô tô") || normalizedCity.contains("bình liêu") -> WeatherData(
                temp = "30",
                description = "Gió biển nhẹ",
                humidity = "70",
                windSpeed = "18.0",
                icon = "02d",
                forecast = generateForecast("Gió nhẹ")
            )
            normalizedCity.contains("đà lạt") || normalizedCity.contains("sa pa") || normalizedCity.contains("mộc châu") -> WeatherData(
                temp = "18",
                description = "Trời se lạnh",
                humidity = "80",
                windSpeed = "8.5",
                icon = "03d",
                forecast = generateForecast("Lạnh")
            )
            normalizedCity.contains("nha trang") || normalizedCity.contains("mũi né") || normalizedCity.contains("vũng tàu") -> WeatherData(
                temp = "31",
                description = "Biển xanh nắng vàng",
                humidity = "65",
                windSpeed = "14.0",
                icon = "01d",
                forecast = generateForecast("Nắng biển")
            )
            normalizedCity.contains("phú quốc") || normalizedCity.contains("đảo phú quý") -> WeatherData(
                temp = "30",
                description = "Nắng nhẹ, gió mát",
                humidity = "75",
                windSpeed = "16.5",
                icon = "02d",
                forecast = generateForecast("Biển êm")
            )
            normalizedCity.contains("ninh bình") || normalizedCity.contains("huế") -> WeatherData(
                temp = "27",
                description = "Trời dịu mát",
                humidity = "68",
                windSpeed = "11.0",
                icon = "04d",
                forecast = generateForecast("Mát mẻ")
            )
            normalizedCity.contains("cần thơ") -> WeatherData(
                temp = "33",
                description = "Nắng ấm miền Tây",
                humidity = "72",
                windSpeed = "9.5",
                icon = "02d",
                forecast = generateForecast("Nắng ấm")
            )
            else -> null
        }
    }

    private fun generateForecast(baseDesc: String): List<ForecastItem> {
        val days = listOf("Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ Nhật")
        return List(10) { i ->
            ForecastItem(
                day = days[i % 7],
                icon = if (baseDesc.contains("Nắng")) "01d" else "02d",
                temp = "${18 + (i * 2 % 10)}°C"
            )
        }
    }

    private fun getSuggestion(weather: WeatherData): TravelSuggestion {
        return when {
            weather.description.contains("nắng") -> TravelSuggestion(
                cityName = "Đà Nẵng",
                description = "Thành phố của những cây cầu.",
                reason = "Thời tiết nắng đẹp rất thích hợp để tắm biển Mỹ Khê và tham quan Bà Nà Hills.",
                imageUrl = "https://sacotravel.com/wp-content/uploads/2023/07/da-nang.jpg?w=800"
            )
            weather.description.contains("lạnh") || weather.temp.toInt() < 25 -> TravelSuggestion(
                cityName = "Đà Lạt",
                description = "Thành phố ngàn hoa.",
                reason = "Không khí se lạnh thích hợp để thưởng thức cà phê và ngắm hoa dã quỳ.",
                imageUrl = "https://static.vinwonders.com/production/thanh-pho-da-lat-7.jpg?w=800"
            )
            else -> TravelSuggestion(
                cityName = "Hạ Long",
                description = "Vịnh Hạ Long - Kỳ quan thế giới.",
                reason = "Trời mát mẻ, biển êm, lý tưởng để đi du thuyền ngắm cảnh vịnh.",
                imageUrl = "https://truyenhinhnghean.vn/file/4028eaa46735a26101673a4df345003c/052024/2024-05-12_180644_20240512180729.jpg?w=800"
            )
        }
    }
}
