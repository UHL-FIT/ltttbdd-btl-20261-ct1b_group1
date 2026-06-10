//model booking dùng cho business logic/UI
package com.example.dattuadulich.domain.model

data class Booking(
    val maDatTour: String,       // ID booking, dùng UUID
    val tenDiaDiem: String,      // Tên địa điểm tour
    val anhDiaDiem: String,      // URL ảnh địa điểm
    val tenKhachHang: String,    // Họ tên người đặt
    val sdtKhachHang: String,    // Số điện thoại
    val ngayKhoiHanh: String,    // Ngày khởi hành (VD: "20/10/2024")
    val soNguoi: Int,            // Số người đi
    val tongTien: Double,        // Tổng tiền = giá * số người
    val ngayDat: Long            // Timestamp lúc đặt
)