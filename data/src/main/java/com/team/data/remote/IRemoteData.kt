package com.team.data.remote

import com.team.entities.weather.remote.response.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IRemoteData {

    @GET("forecast")
    fun getWeather(@Query("q") city: String, @Query("appid") apiKey: String): Single<WeatherResponse>
}