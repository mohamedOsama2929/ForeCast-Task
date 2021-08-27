package com.team.domain.domainRepo

import com.team.entities.weather.local.WeatherLocal
import com.team.entities.weather.models.Weather
import com.team.entities.weather.remote.response.WeatherResponse
import io.reactivex.Single

interface IDomainRepo {
    /*----------------------------------------Remote----------------------------------------*/
    fun getWeather(city: String, apiKey:String): Single<WeatherResponse>

    /*----------------------------------------Local----------------------------------------*/
    fun cacheWeather(weather: Weather)

    fun getCachedWeather(city: String):Single<WeatherLocal>
}