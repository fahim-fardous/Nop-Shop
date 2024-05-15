package com.example.nopshop.model

import android.graphics.drawable.Drawable

data class BestSellingItem(
    var id:Int,
    val productImage:Int,
    val productName:String,
    var rating:Int,
    val price:Double
)
