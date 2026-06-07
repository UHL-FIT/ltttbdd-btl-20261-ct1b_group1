package com.example.dattuadulich.ui.screen.mybooking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dattuadulich.data.local.AppDatabase
import com.example.dattuadulich.data.local.DatTourEntity
import com.example.dattuadulich.repository.BookingRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MyBookingViewModel(private val repository: BookingRepository) : ViewModel() {

    // 1. Tự động lôi danh sách hóa đơn từ Database lên
    val historyList: StateFlow<List<DatTourEntity>> = repository.getAllBookings()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // 2. Hàm Hủy Tour
    fun xoaHoaDon(hoaDon: DatTourEntity) {
        viewModelScope.launch {
           repository.deleteBooking(hoaDon)
        }
    }
}