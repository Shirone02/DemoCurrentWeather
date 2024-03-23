package com.example.democurrentweather.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.democurrentweather.models.CurrentWeatherResponse
import com.example.democurrentweather.repo.WeatherRepo
import com.example.democurrentweather.response.BaseResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class WeatherViewModel(val weatherRepo: WeatherRepo, application: Application ): AndroidViewModel(application){


    val currentWeatherResult: MutableLiveData<BaseResponse<CurrentWeatherResponse>> =
        MutableLiveData()

    var currentWeatherReponse: CurrentWeatherResponse?=null

    fun getCurrentWeatherByCityName(cityName: String){
        viewModelScope.launch {
            currentWeatherResult.postValue(BaseResponse.Loading())
            val response = weatherRepo.getCurrentWeatherByCityName(cityName);
            currentWeatherResult.postValue(handleCurrentWeatherResponse(response))
        }
    }

    private fun handleCurrentWeatherResponse(response: Response<CurrentWeatherResponse>): BaseResponse<CurrentWeatherResponse> {
        if (response.isSuccessful && response.code() == 200) {
            response.body()?.let { it ->
                currentWeatherReponse = it
                return BaseResponse.Success(it)
            }
        }
        return BaseResponse.Error(null, response.message())
    }
}