package com.example.dattuadulich.repository

import com.example.dattuadulich.data.remote.RetrofitInstance
import com.example.dattuadulich.data.remote.dto.WeatherResponse
//thằng nhân viên này sẵn sàng lấy dữ liệu về dựa trên retro instance đã được đăng kí kết nối api lên server
class PlaceRepository {
    private val api = RetrofitInstance.api
    private val apiKey = "45296858da08a043f326210576ac19a3"

    suspend fun getWeather(city: String): WeatherResponse {
        return api.getWeather(city, apiKey)
    }
}
