package com.example.nopshop.db.dbmodel.slider

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nopshop.model.slider.Slider

@Entity(tableName = "slider")
data class SliderEntity(
    @PrimaryKey
    val Id: Int,
    val EntityId: Int,
    val ImageUrl: String,
    val SliderType: Int
)

fun SliderEntity.toSlider() = Slider(
    Id = Id,
    EntityId = EntityId,
    ImageUrl = ImageUrl,
    SliderType = SliderType
)
