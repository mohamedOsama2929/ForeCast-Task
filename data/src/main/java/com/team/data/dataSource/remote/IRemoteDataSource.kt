package com.team.data.dataSource.remote

import com.team.entities.weather.remote.response.WeatherResponse
import io.reactivex.Single

interface IRemoteDataSource {

    fun getWeather(city: String, apiKey: String): Single<WeatherResponse>
}