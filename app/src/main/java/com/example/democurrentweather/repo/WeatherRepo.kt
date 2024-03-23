package com.example.democurrentweather.repo

import com.example.democurrentweather.Constants.API_KEY
import com.example.democurrentweather.RetrofitClient

class WeatherRepo {
    suspend fun getCurrentWeatherByCityName(cityName: String) =
        RetrofitClient.getWeatherAPI.getCurrentWeatherByCityName(cityName, API_KEY)
}