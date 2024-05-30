package com.example.nopshop.db.dbmodel.slider

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "slider")
data class SliderEntity(
    @PrimaryKey
    val Id: Int,
    val EntityId: Int,
    val ImageUrl: String,
    val SliderType: Int
)
