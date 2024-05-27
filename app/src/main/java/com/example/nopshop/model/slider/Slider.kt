package com.example.nopshop.model.slider

import com.example.nopshop.db.dbmodel.slider.SliderEntity


data class Slider(
    val EntityId: Int,
    val Id: Int,
    val ImageUrl: String,
    val SliderType: Int
)

fun Slider.asEntity() = SliderEntity(
    EntityId = EntityId,
    Id = Id,
    ImageUrl = ImageUrl,
    SliderType = SliderType

)