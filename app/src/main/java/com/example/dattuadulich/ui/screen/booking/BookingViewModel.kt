package com.example.dattuadulich.ui.screen.booking

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dattuadulich.data.local.DatTourEntity
import com.example.dattuadulich.repository.BookingRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

// Cái rổ để hứng dữ liệu từ file JSON
data class TourInfo(
    val giaTien: Double,
    val anhDiaDiem: String
)
class BookingViewModel(
    app: Application,
    private val repository: BookingRepository
) : AndroidViewModel(app) {
    private val _uiState = MutableStateFlow(BookingUiState())
    val uiState: StateFlow<BookingUiState> = _uiState.asStateFlow()

    // Hàm đọc file Json
    fun taiThongTinTour(tenThanhPho: String) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                // mở file json
                val jsonString = getApplication<Application>().assets
                    .open("bang_gia.json")
                    .bufferedReader()
                    .use { it.readText() }
                // dịch json sang map của kotlin
                val type = object : TypeToken<Map<String, TourInfo>>() {}.type
                val bangGia: Map<String, TourInfo> = Gson().fromJson(jsonString, type)
                //tra cứu tên thành phố
                val thongTin = bangGia[tenThanhPho]
                if(thongTin != null) {
                    _uiState.update { 
                        it.copy(
                            giaTien = thongTin.giaTien,
                            anhDiaDiem = thongTin.anhDiaDiem,
                            isLoading = false
                        ) 
                    }
                } else {
                    // Nếu thành phố lạ chưa có trong JSON thì gán mặc định
                    _uiState.update { 
                        it.copy(
                            giaTien = 1000000.0,
                            anhDiaDiem = "https://images.unsplash.com/photo-1528127269322-539801943592?w=800&q=80",
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
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
        onSucess: () -> Unit,
        ){
        viewModelScope.launch {

            val tongTien = uiState.value.giaTien * soNguoi
            //đóng gói entity
            val hoaDon = DatTourEntity(
                maDatTour = UUID.randomUUID().toString(),
                tenDiaDiem = tenDiadiem,
                anhDiaDiem =  anhDiaDiem,
                tenKhachHang = tenKhachHang,
                sdtKhachHang = sdtKhachHang,
                ngayKhoiHanh = ngayKhoiHanh,
                soNguoi = soNguoi,
                tongTien = tongTien,
                ngayDat = System.currentTimeMillis()
            )
            // lưu vào database
            repository.insertBooking(hoaDon)
            //báo thành công
            onSucess()
        }
    }
}