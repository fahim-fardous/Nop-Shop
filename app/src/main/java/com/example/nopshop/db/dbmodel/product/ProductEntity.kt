package com.example.nopshop.db.dbmodel.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val productId:Int,
    val productName:String,
    val productImage:String,
    val productShortDescription:String,
    val productLongDescription:String,
    val oldPrice:String,
    val newPrice:String,
    val stock:String
)


