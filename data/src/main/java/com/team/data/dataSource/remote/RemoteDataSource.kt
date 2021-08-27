package com.team.data.dataSource.remote

import com.team.data.remote.IRemoteData
import com.team.entities.weather.remote.response.WeatherResponse
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val iRemoteData: IRemoteData) : IRemoteDataSource {

    override fun getWeather(city: String, apiKey: String): Single<WeatherResponse> {
        return iRemoteData.getWeather(city, apiKey)
    }
}