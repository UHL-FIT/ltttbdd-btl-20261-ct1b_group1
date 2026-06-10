//model địa điểm chính của app
package com.example.dattuadulich.domain.model

data class Place(
    val id: String,              // ID địa điểm
    val name: String,            // Tên địa điểm (VD: "Vịnh Hạ Long")
    val city: String,            // Tỉnh/thành phố (VD: "Quảng Ninh")
    val imageUrl: String,        // URL ảnh đại diện
    val description: String,     // Mô tả ngắn
    val temperature: Double,     // Nhiệt độ hiện tại (từ API thời tiết)
    val humidity: Int,           // Độ ẩm (%)
    val windSpeed: Double,       // Tốc độ gió (km/h)
    val price: Double            // Giá tour tham khảo
)