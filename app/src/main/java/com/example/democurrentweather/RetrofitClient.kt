package com.example.democurrentweather


import com.example.democurrentweather.api.WeatherApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        val instances:Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }


        val getWeatherAPI:WeatherApi = instances.create(WeatherApi::class.java)
    }
}


