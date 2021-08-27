package com.team.entities.local

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Entity")
class Entity {

    @PrimaryKey(autoGenerate = false)
    var id: Int = 1
}