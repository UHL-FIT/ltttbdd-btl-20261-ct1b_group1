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

    // 1. Tự động lôi danh sách hóa đơn từ Database lên và ánh xạ sang MyBookingUiState
    val uiState: StateFlow<MyBookingUiState> = repository.getAllBookings()
        .map { danhSach ->
            MyBookingUiState(//tính toán chi tiêu số thống kê
                historyList = danhSach,
                tongSoTour = danhSach.size,
                tongTien = danhSach.sumOf { it.tongTien },
                maxTour = danhSach.maxByOrNull { it.tongTien },
                minTour = danhSach.minByOrNull { it.tongTien }
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000),
            initialValue = MyBookingUiState()
        )

    // 2. Hàm Cập nhật Tour
    fun capNhatHoaDon(hoaDon: DatTourEntity) {
        viewModelScope.launch {
            repository.updateBooking(hoaDon)
        }
    }

    // 3. Hàm Hủy Tour
    fun xoaHoaDon(hoaDon: DatTourEntity) {
        viewModelScope.launch {
           repository.deleteBooking(hoaDon)
        }
    }
}