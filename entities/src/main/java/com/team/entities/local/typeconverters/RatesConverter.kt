package com.team.entities.local.typeconverters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RatesConverter {

    @TypeConverter
    fun storedStringToRates(value: String): Map<String, Double>? {
        val myType = object : TypeToken<Map<String, Double>?>() {}.type
        return Gson().fromJson(value, myType)
    }

    @TypeConverter
    fun ratesToStoredString(cl: Map<String, Double>): String? {
        return Gson().toJson(cl)
    }
}