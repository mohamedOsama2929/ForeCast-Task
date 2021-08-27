package com.team.entities.weather.remote.request

data class WeatherRequest(
    val city: String? = null,
    val apiKey: String = "54869e72895250128aa6cddb65ea2f9a"
)
