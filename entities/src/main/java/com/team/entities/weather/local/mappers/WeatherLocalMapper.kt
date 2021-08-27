package com.team.entities.weather.local.mappers

import com.team.entities.utils.EntityMapper
import com.team.entities.weather.local.WeatherLocal
import com.team.entities.weather.models.Weather
import javax.inject.Inject

class WeatherLocalMapper
@Inject
constructor() : EntityMapper<WeatherLocal, Weather> {

    override fun mapFromEntity(entity: WeatherLocal): Weather {
        return Weather(
            cnt = entity.cnt,
            city = entity.city,
            cod = entity.cod,
            message = entity.message,
            list = entity.list,
        )
    }

    override fun mapToEntity(domainModel: Weather): WeatherLocal {
        return WeatherLocal(
            cnt = domainModel.cnt,
            city = domainModel.city,
            cod = domainModel.cod,
            message = domainModel.message,
            list = domainModel.list,
            name= domainModel.city?.name
        )
    }

    fun mapFromEntityList(entities: List<WeatherLocal>): List<Weather> {
        return entities.map { mapFromEntity(it) }
    }
}


