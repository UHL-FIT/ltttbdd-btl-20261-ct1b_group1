package com.example.dattuadulich.data.remote

import com.example.dattuadulich.data.remote.dto.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceApiService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherResponse
}
