package com.example.democurrentweather

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.democurrentweather.models.Weather
import com.example.democurrentweather.repo.WeatherRepo
import com.example.democurrentweather.response.BaseResponse
import com.example.democurrentweather.ui.WeatherViewModel
import com.example.democurrentweather.ui.WeatherViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var currentTemperatureTextView: TextView
    private lateinit var currentDescriptionTextView: TextView
    lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currentTemperatureTextView = findViewById(R.id.temperature)
        currentDescriptionTextView = findViewById(R.id.description)

        val weatherRepo = WeatherRepo()
        val weatherViewModelFactory = WeatherViewModelFactory(weatherRepo, application)

        weatherViewModel =
            ViewModelProvider(this, weatherViewModelFactory)[WeatherViewModel::class.java]
        weatherViewModel.getCurrentWeatherByCityName("Hà nội")
        weatherViewModel.currentWeatherResult.observe(this, Observer { response ->
            when(response){
                is BaseResponse.Success ->{
                    val weatherData = response.data
                    weatherData?.let {
                        currentTemperatureTextView.text = it.main.toString()
                        //currentDescriptionTextView.text = it.weather.toString()
                    }
                }

                is BaseResponse.Error ->{
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }
                is BaseResponse.Loading -> {}
            }
        })

    }
}