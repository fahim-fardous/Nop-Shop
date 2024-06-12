package com.example.nopshop.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nopshop.db.dbmodel.slider.SliderEntity


@Dao
interface SliderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveImageSlider(imageSliderEntity: List<SliderEntity>)

    @Query("SELECT * FROM slider")
    suspend fun getImageSlider(): List<SliderEntity>

}