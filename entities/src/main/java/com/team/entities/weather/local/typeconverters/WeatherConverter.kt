package com.team.entities.weather.local.typeconverters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.team.entities.weather.remote.response.ListItem

class WeatherConverter {

    @TypeConverter
    fun storedStringToWeathers(value: String): List<ListItem>? {
        val myType = object : TypeToken<List<ListItem>?>() {}.type
        return Gson().fromJson(value, myType)
    }

    @TypeConverter
    fun weathersToStoredString(cl: List<ListItem>): String? {
        return Gson().toJson(cl)
    }
}