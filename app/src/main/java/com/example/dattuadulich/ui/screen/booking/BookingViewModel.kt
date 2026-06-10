package com.example.dattuadulich.ui.screen.booking

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dattuadulich.data.local.DatTourEntity
import com.example.dattuadulich.repository.BookingRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

// Cái rổ để hứng dữ liệu từ file JSON
data class TourInfo(
    val giaTien: Double,
    val anhDiaDiem: String
)
class BookingViewModel(private val context: Context, private val repository: BookingRepository) : ViewModel() {
    private val _giaTien = MutableStateFlow(0.0)
    val giaTien: StateFlow<Double> = _giaTien.asStateFlow()

    private  val _anhDiaDiem = MutableStateFlow("")
    // Hàm đọc file Json
    fun taiThongTinTour(tenThanhPho: String) {
        try {
            // mở file json
            val jsonString = context.assets.open("bang_gia.json").bufferedReader().use { it.readText() }
            // dịch json sang map của kotlin
            val type = object : TypeToken<Map<String, TourInfo>>() {}.type
            val bangGia: Map<String, TourInfo> = Gson().fromJson(jsonString, type)
            //tra cứu tên thành phố
            val thongTin = bangGia[tenThanhPho]
            if(thongTin !=null)
            {
                _giaTien.value = thongTin.giaTien
                _anhDiaDiem.value = thongTin.anhDiaDiem
            } else {
                // Nếu thành phố lạ chưa có trong JSON thì gán mặc định
                _giaTien.value = 1000000.0
                _anhDiaDiem.value = "https://images.unsplash.com/photo-1528127269322-539801943592?w=800&q=80"
            }

        }catch (e: Exception) {
            e.printStackTrace()
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

            val tongTien = giaTien.value * soNguoi
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