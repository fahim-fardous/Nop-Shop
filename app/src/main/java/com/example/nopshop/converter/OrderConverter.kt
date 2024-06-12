package com.example.nopshop.converter

import androidx.room.TypeConverter
import com.example.nopshop.model.cart.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OrderConverter {
    @TypeConverter
    fun fromItemList(value: List<Item>): String {
        val type = object : TypeToken<List<Item>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toItemList(value: String): List<Item> {
        val type = object : TypeToken<List<Item>>() {}.type
        return Gson().fromJson(value, type)
    }
}