package com.example.nopshop.model

data class ProductItem(
    var id:Long,
    val productImage:Int,
    val productName:String,
    var rating:Float,
    val price:Double
)
