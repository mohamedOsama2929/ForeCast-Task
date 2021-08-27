package com.team.forecast.ui.weather

import android.annotation.SuppressLint
import android.util.Log
import com.team.domain.use_case.weather.local.CacheWeatherUseCase
import com.team.domain.use_case.weather.local.GetCachedWeatherUseCase
import androidx.lifecycle.MutableLiveData
import com.team.core.utils.NetworkState
import com.team.core.utils.netWorkError
import com.team.domain.use_case.weather.remote.WeatherUseCase
import com.team.entities.weather.local.mappers.WeatherLocalMapper
import com.team.entities.weather.models.Weather
import com.team.entities.weather.remote.mappers.WeatherRemoteMapper
import com.team.entities.weather.remote.request.WeatherRequest
import com.team.forecast.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherUseCase: WeatherUseCase,
    private val cacheWeatherUseCase: CacheWeatherUseCase,
    private val getCachedWeatherUseCase: GetCachedWeatherUseCase
) :
    BaseViewModel() {

    private val weatherLiveData = MutableLiveData<Weather>()

    @Inject
    lateinit var weatherRemoteMapper: WeatherRemoteMapper

    @Inject
    lateinit var weatherLocalMapper: WeatherLocalMapper

    fun getWeather(city: String) {
        networkState.postValue(NetworkState.LOADING)
        addToDisposable(
            weatherUseCase.execute(WeatherRequest(city = city))
                .subscribe
                    ({ weather ->
                    networkState.postValue(NetworkState.LOADED)
                    weatherLiveData.postValue(weatherRemoteMapper.mapFromEntity(weather))
                    cacheWeather(weatherRemoteMapper.mapFromEntity(weather))
                },
                    {
                        networkState.postValue(NetworkState.ERROR)
                        getCachedWeather(city)
                        errorState.postValue(netWorkError(it))
                    }
                )
        )
    }

    fun getCachedWeather(city: String) {
        networkState.postValue(NetworkState.LOADING)
        addToDisposable(
            getCachedWeatherUseCase.execute(city)
                .subscribe
                    ({ weather ->
                    networkState.postValue(NetworkState.LOADED)
                    weatherLiveData.postValue(weatherLocalMapper.mapFromEntity(weather))
                },
                    {
                        // TODO: 8/27/2021 show sorry error
                        networkState.postValue(NetworkState.ERROR)
                        errorState.postValue(netWorkError(it))
                    }
                )
        )
    }

    @SuppressLint("CheckResult")
    private fun cacheWeather(weather: Weather) {
        cacheWeatherUseCase.execute(weather).subscribe(
            {
                Log.e("TAG", "cacheWeather: Success")
            },
            {
                Log.e("TAG", "cacheWeather: ${it.message}")
            }
        )
    }

    fun getWeatherData() = weatherLiveData
}