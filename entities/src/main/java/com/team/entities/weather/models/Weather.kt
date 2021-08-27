package com.team.entities.weather.models

import com.team.entities.weather.remote.response.City
import com.team.entities.weather.remote.response.ListItem

data class Weather(
    val city: City? = null,
    val cnt: Int? = null,
    val cod: String? = null,
    val message: Int? = null,
    val list: List<ListItem?>? = null
)
