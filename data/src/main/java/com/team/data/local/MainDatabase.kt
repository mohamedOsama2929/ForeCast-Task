package com.team.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.team.entities.local.Entity
import com.team.entities.local.typeconverters.RatesConverter

@Database(entities = [Entity::class], version = 1)
@TypeConverters(RatesConverter::class)
abstract class MainDatabase : RoomDatabase() {

    abstract fun mainDao(): MainDao

    companion object {
        val DATABASE_NAME: String = "main_db"
    }


}