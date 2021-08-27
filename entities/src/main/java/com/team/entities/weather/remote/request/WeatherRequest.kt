package com.team.entities.weather.remote.request

data class WeatherRequest(
    val city: String? = null,
    val apiKey: String = "33faf1e1239d19d363c9d897d98e0443"
)
