package com.example.democurrentweather.ui

import android.app.Application
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.democurrentweather.repo.WeatherRepo

class WeatherViewModelFactory(
    private val weatherRepository:WeatherRepo,
    private val application: Application
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherViewModel(weatherRepository, application) as T
    }
}