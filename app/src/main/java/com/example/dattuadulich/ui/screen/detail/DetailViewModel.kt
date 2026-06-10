package com.example.dattuadulich.ui.screen.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {
    private val _uiState = mutableStateOf(DetailUiState())
    val uiState: State<DetailUiState> = _uiState

    fun fetchDetail(name: String) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        // Giả lập gọi API
        val mockData = when {
            name.contains("Hạ Long") -> DetailUiState(
                false, "Vịnh Hạ Long",
                "https://images.unsplash.com/photo-1764645859246-8c6db98330a7?w=800&q=80",
                "Vịnh Hạ Long là di sản thiên nhiên thế giới được UNESCO công nhận, với hàng nghìn đảo đá vôi kỳ vĩ mọc lên từ làn nước xanh ngọc bích. Đây là điểm đến không thể bỏ qua khi tới Việt Nam."
            )
            name.contains("Đà Nẵng") -> DetailUiState(
                false, "Đà Nẵng",
                "https://images.unsplash.com/photo-1440694997168-8ae4033554c7?w=800&q=80",
                "Thành phố của những cây cầu và bãi biển Mỹ Khê xinh đẹp. Đà Nẵng kết hợp hài hòa giữa nhịp sống hiện đại và vẻ đẹp tự nhiên hoang sơ."
            )
            name.contains("Đà Lạt") -> DetailUiState(
                false, "Đà Lạt",
                "https://samtenhills.vn/wp-content/uploads/2024/11/kinh-nghiem-du-lich-da-lat-1-minh.jpg?w=800&q=80",
                "Thành phố ngàn hoa với không khí se lạnh quanh năm. Những đồi thông bạt ngàn và những thác nước hùng vĩ sẽ làm say lòng bất cứ du khách nào."
            )
            name.contains("Hà Nội") -> DetailUiState(
                false, "Thủ đô Hà Nội",
                "https://images.unsplash.com/photo-1725550798518-f551648e4c10?w=800&q=80",
                "Trái tim của Việt Nam với nghìn năm văn hiến. Hồ Gươm, Phố Cổ và những món ăn đường phố đặc sắc đang chờ bạn khám phá."
            )
            name.contains("TP Hồ Chí Minh") -> DetailUiState(
                false, "TP Hồ Chí Minh",
                "https://images.unsplash.com/photo-1583417319070-4a69db38a482?w=800",
                "Trung tâm kinh tế lớn nhất Việt Nam với nhịp sống sôi động. Thành phố nổi tiếng với Chợ Bến Thành, Nhà thờ Đức Bà và tòa nhà Landmark 81 hiện đại."
            )
            else -> DetailUiState(
                false, name,
                "https://images.unsplash.com/photo-1528127269322-539801943592?w=800&q=80",
                "Khám phá vẻ đẹp tiềm ẩn tại $name. Một chuyến đi đầy hứa hẹn với những trải nghiệm tuyệt vời và kỷ niệm khó quên."
            )
        }
        _uiState.value = mockData
    }
}