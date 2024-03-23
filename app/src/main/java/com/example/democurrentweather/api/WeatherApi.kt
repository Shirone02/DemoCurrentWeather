package com.example.democurrentweather.api

import com.example.democurrentweather.Constants
import com.example.democurrentweather.models.CurrentWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(Constants.CURRENT_WEATHER_URL)
    suspend fun getCurrentWeatherByCityName(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("lang") lang: String? = "en"
    ): Response<CurrentWeatherResponse>
}