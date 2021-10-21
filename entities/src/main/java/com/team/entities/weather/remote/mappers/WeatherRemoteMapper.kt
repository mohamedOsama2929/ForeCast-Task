package com.team.entities.weather.remote.mappers

import com.team.entities.utils.EntityMapper
import com.team.entities.weather.models.Weather
import com.team.entities.weather.remote.response.ListItem
import com.team.entities.weather.remote.response.WeatherResponse
import javax.inject.Inject

class WeatherRemoteMapper
@Inject
constructor() : EntityMapper<WeatherResponse, Weather> {

    override fun mapFromEntity(entity: WeatherResponse): Weather {
        return Weather(
            cnt = entity.cnt,
            city = entity.city,
            cod = entity.cod,
            message = entity.message,
            list = entity.list,
        )
    }

    override fun mapToEntity(domainModel: Weather): WeatherResponse {
        return WeatherResponse(
            cnt = domainModel.cnt,
            city = domainModel.city,
            cod = domainModel.cod,
            message = domainModel.message,
            list = domainModel.list ,
        )
    }

    fun mapFromEntityList(entities: List<WeatherResponse>): List<Weather> {
        return entities.map { mapFromEntity(it) }
    }
}


