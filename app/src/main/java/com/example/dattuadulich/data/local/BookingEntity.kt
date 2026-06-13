// định nghĩa cấu trúc bảng database
package com.example.dattuadulich.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dat_tour")
//model dữ liệu lưu trữ trong database
data class DatTourEntity(
    @PrimaryKey
    val maDatTour: String,
    val tenDiaDiem: String,
    val anhDiaDiem: String,
    val tenKhachHang: String,
    val sdtKhachHang: String,
    val ngayKhoiHanh: String,
    val soNguoi: Int,
    val tongTien: Double,
    val ngayDat: Long

)
