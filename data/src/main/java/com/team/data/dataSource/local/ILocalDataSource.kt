package com.team.data.dataSource.local

import com.team.entities.weather.local.WeatherLocal
import com.team.entities.weather.models.Weather
import io.reactivex.Single


interface ILocalDataSource {

    fun cacheWeather(weather: Weather)
    fun getCachedWeather(cityName:String):Single<WeatherLocal>
}