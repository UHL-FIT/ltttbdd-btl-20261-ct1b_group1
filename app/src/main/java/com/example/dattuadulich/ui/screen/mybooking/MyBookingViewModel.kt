package com.example.dattuadulich.ui.screen.mybooking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dattuadulich.data.local.DatTourEntity
import com.example.dattuadulich.repository.BookingRepository
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MyBookingViewModel(private val repository: BookingRepository) : ViewModel() {

    // 1. Tự động lôi danh sách hóa đơn từ Database lên
    val historyList: StateFlow<List<DatTourEntity>> = repository.getAllBookings()
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000),
            initialValue = emptyList()
        )
    // THÊM ĐOẠN NÀY ĐỂ TỰ ĐỘNG TÍNH TOÁN KHI CÓ SỰ THAY ĐỔI
    val tongSoTour = historyList.map { danhSach -> danhSach.size }
        .stateIn(viewModelScope, WhileSubscribed(5000), 0)

    val tongTien = historyList.map { danhSach -> danhSach.sumOf { it.tongTien } }
        .stateIn(viewModelScope, WhileSubscribed(5000), 0.0)

    val maxTour = historyList.map { danhSach -> danhSach.maxByOrNull { it.tongTien } }
        .stateIn(viewModelScope, WhileSubscribed(5000), null)

    val minTour = historyList.map { danhSach -> danhSach.minByOrNull { it.tongTien } }
        .stateIn(viewModelScope, WhileSubscribed(5000), null)
    // 2. Hàm Hủy Tour
    fun xoaHoaDon(hoaDon: DatTourEntity) {
        viewModelScope.launch {
           repository.deleteBooking(hoaDon)
        }
    }
}