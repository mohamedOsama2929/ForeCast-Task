package com.team.data.repo

import com.team.data.dataSource.local.ILocalDataSource
import com.team.data.dataSource.remote.IRemoteDataSource
import com.team.domain.domainRepo.IDomainRepo
import com.team.entities.weather.local.WeatherLocal
import com.team.entities.weather.models.Weather
import com.team.entities.weather.remote.response.WeatherResponse
import io.reactivex.Single
import javax.inject.Inject

class Repo @Inject constructor(
    private val iRemoteDataSource: IRemoteDataSource,
    private val iLocalDataSource: ILocalDataSource
) : IDomainRepo {
    /*----------------------------------------Remote----------------------------------------*/

    override fun getWeather(city: String, apiKey: String): Single<WeatherResponse> {
        return iRemoteDataSource.getWeather(city, apiKey)
    }

    /*----------------------------------------Local----------------------------------------*/
    override fun cacheWeather(weather: Weather) {
        return iLocalDataSource.cacheWeather(weather)
    }

    override fun getCachedWeather(city: String): Single<WeatherLocal> {
        return iLocalDataSource.getCachedWeather(city)
    }
}