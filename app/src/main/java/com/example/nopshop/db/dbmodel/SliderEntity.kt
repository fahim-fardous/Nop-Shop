package com.example.nopshop.db.dbmodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "slider")
data class SliderEntity(
    @PrimaryKey(autoGenerate = true)
    val Id: Int,
    val EntityId: Int,
    val ImageUrl: String,
    val SliderType: Int
)
