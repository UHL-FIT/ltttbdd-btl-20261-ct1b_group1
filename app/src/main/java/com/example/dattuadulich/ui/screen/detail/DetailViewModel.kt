package com.example.dattuadulich.ui.screen.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {
    private val _uiState = mutableStateOf(DetailUiState())
    val uiState: State<DetailUiState> = _uiState

    fun fetchDetail(name: String) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        val mockData = when {
            name.contains("Hạ Long") -> DetailUiState(
                false, "Vịnh Hạ Long",
                "https://images.unsplash.com/photo-1524231757912-21f4fe3a7200?w=800&q=80",
                "Vịnh Hạ Long là di sản thiên nhiên thế giới được UNESCO công nhận, với hàng nghìn đảo đá vôi kỳ vĩ mọc lên từ làn nước xanh ngọc bích."
            )
            name.contains("Đà Nẵng") -> DetailUiState(
                false, "Đà Nẵng",
                "https://images.unsplash.com/photo-1559592442-998818451124?w=800&q=80",
                "Thành phố của những cây cầu và bãi biển Mỹ Khê xinh đẹp."
            )
            name.contains("Đà Lạt") -> DetailUiState(
                false, "Đà Lạt",
                "https://images.unsplash.com/photo-1589182397057-b16174fe924d?w=800&q=80",
                "Thành phố ngàn hoa với không khí se lạnh quanh năm."
            )
            name.contains("Hà Nội") -> DetailUiState(
                false, "Thủ đô Hà Nội",
                "https://images.unsplash.com/photo-1555931466-17955c747d52?w=800&q=80",
                "Trái tim của Việt Nam với nghìn năm văn hiến."
            )
            else -> DetailUiState(
                false, name,
                "https://images.unsplash.com/photo-1528127269322-539801943592?w=800&q=80",
                "Khám phá vẻ đẹp tiềm ẩn tại $name."
            )
        }
        _uiState.value = mockData
    }
}