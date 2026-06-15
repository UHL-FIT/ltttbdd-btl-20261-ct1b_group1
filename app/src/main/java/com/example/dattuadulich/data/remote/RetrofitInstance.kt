package com.example.dattuadulich.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//cấu hình bộ máy để kết nối internet
object RetrofitInstance {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    val api: PlaceApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlaceApiService::class.java)
    }
}
