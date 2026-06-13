package com.example.dattuadulich.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("main") val main: Main,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("name") val name: String
)

data class Main(
    @SerializedName("temp") val temp: Double,
    @SerializedName("humidity") val humidity: Int
)

data class Weather(
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class Wind(
    @SerializedName("speed") val speed: Double
)

//model dữ liệu tour
data class TourModel(
    val id: Int,
    val title: String,
    val location: String,
    val price: String,
    val imageUrl: String,
    val rating: Double
)
