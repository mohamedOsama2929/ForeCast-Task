package com.team.entities.weather.local

import android.arch.persistence.room.*
import com.team.entities.weather.remote.response.City
import com.team.entities.weather.remote.response.ListItem
@Entity(tableName = "weather")
data class WeatherLocal(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "cnt")
    val cnt: Int? = null,
    @ColumnInfo(name = "name")
    val name: String? = null,
    @ColumnInfo(name = "city")
    val city: City? = null,
    @ColumnInfo(name = "cod")
    val cod: String? = null,
    @ColumnInfo(name = "message")
    val message: Int? = null,
    @ColumnInfo(name = "list")
    val list: List<ListItem?>? = null
)
