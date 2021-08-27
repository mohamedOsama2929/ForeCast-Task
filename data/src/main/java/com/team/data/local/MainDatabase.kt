package com.team.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.team.entities.weather.local.WeatherLocal
import com.team.entities.weather.local.typeconverters.CityConverter
import com.team.entities.weather.local.typeconverters.WeatherConverter

@Database(entities = [WeatherLocal::class], version = 1,exportSchema = true)
@TypeConverters(WeatherConverter::class, CityConverter::class)
abstract class MainDatabase : RoomDatabase() {

    abstract fun mainDao(): MainDao

    companion object {
        const val DATABASE_NAME: String = "main_db"
    }
}