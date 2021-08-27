package com.team.entities.weather.local.typeconverters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.team.entities.weather.remote.response.City
import com.team.entities.weather.remote.response.ListItem

class CityConverter {

    @TypeConverter
    fun storedStringToCity(value: String): City? {
        val myType = object : TypeToken<City?>() {}.type
        return Gson().fromJson(value, myType)
    }

    @TypeConverter
    fun cityToStoredString(cl: City): String? {
        return Gson().toJson(cl)
    }
}