package com.team.data.dataSource.local

import com.team.data.local.MainDao
import com.team.entities.weather.local.WeatherLocal
import com.team.entities.weather.local.mappers.WeatherLocalMapper
import com.team.entities.weather.models.Weather
import io.reactivex.Single
import javax.inject.Inject

class LocalDataSource @Inject constructor(val mainDao: MainDao) : ILocalDataSource {


    @Inject
    lateinit var weatherCacheMapper: WeatherLocalMapper


    override fun cacheWeather(weather: Weather) {
        return mainDao.insertAll(weatherCacheMapper.mapToEntity(weather))
    }

    override fun getCachedWeather(cityName: String):Single<WeatherLocal> {
        return mainDao.getCachedWeather(cityName)
    }
}