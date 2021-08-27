package com.team.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.team.entities.weather.local.WeatherLocal

import io.reactivex.Single

@Dao
interface MainDao {

     @Query("SELECT * FROM weather WHERE name LIKE :cityName")
     fun getCachedWeather(cityName:String): Single<WeatherLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg weatherLocal: WeatherLocal)
}