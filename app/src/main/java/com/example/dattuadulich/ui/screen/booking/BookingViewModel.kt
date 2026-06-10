package com.example.dattuadulich.ui.screen.booking

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dattuadulich.data.local.DatTourEntity
import com.example.dattuadulich.repository.BookingRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID

class BookingViewModel(
    private val context: Context,
    private val repository: BookingRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookingUiState())
    val uiState: StateFlow<BookingUiState> = _uiState.asStateFlow()

    fun taiThongTinTour(tenThanhPho: String) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                val jsonString = context.assets.open("bang_gia.json").bufferedReader().use { it.readText() }
                val type = object : TypeToken<Map<String, TourInfo>>() {}.type
                val bangGia: Map<String, TourInfo> = Gson().fromJson(jsonString, type)

                val thongTin = bangGia[tenThanhPho]
                if (thongTin != null) {
                    _uiState.update { it.copy(
                        giaTien = thongTin.giaTien,
                        anhDiaDiem = thongTin.anhDiaDiem,
                        isLoading = false
                    )}
                } else {
                    _uiState.update { it.copy(giaTien = 1000000.0, isLoading = false) }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = "Lỗi tải dữ liệu") }
            }
        }
    }

    fun luuHoaDon(
        tenDiadiem: String,
        anhDiaDiem: String,
        tenKhachHang: String,
        sdtKhachHang: String,
        ngayKhoiHanh: String,
        soNguoi: Int,
        onSucess: () -> Unit
    ) {
        viewModelScope.launch {
            val tongTien = _uiState.value.giaTien * soNguoi
            val hoaDon = DatTourEntity(
                maDatTour = UUID.randomUUID().toString(),
                tenDiaDiem = tenDiadiem,
                anhDiaDiem = anhDiaDiem,
                tenKhachHang = tenKhachHang,
                sdtKhachHang = sdtKhachHang,
                ngayKhoiHanh = ngayKhoiHanh,
                soNguoi = soNguoi,
                tongTien = tongTien,
                ngayDat = System.currentTimeMillis()
            )
            repository.insertBooking(hoaDon)
            onSucess()
        }
    }
}