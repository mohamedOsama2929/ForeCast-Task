package com.team.domain.use_case.weather.remote

import com.team.data.remote.IRemoteData
import com.team.entities.weather.remote.request.WeatherRequest
import com.team.entities.weather.remote.response.WeatherResponse
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class WeatherUseCaseTest {

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Mock
    lateinit var repo:IRemoteData

    @Test
    fun buildUseCaseObservable_whenCityIsCorrect_returnWeatherResponse() {
        //arrange
        val weatherRequest = WeatherRequest(city = "cairo")
        val weatherResponse=WeatherResponse()
        //act
        Mockito.`when`(repo.getWeather(city = weatherRequest.city.toString(),apiKey = weatherRequest.apiKey)).thenReturn(
            Single.just(weatherResponse)
        )
        val result=repo.getWeather(city = weatherRequest.city.toString(),apiKey = weatherRequest.apiKey).test()
        //assert
        result.assertNoErrors()
        result.assertComplete()
        assertEquals(weatherResponse, result.values()[0])
    }
}