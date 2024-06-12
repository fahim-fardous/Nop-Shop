package com.example.nopshop.model

import android.media.Image

data class CartItem(
    val id: Long,
    val productName: String,
    val productImage: Int,
    var productQuantity: Long,
    val originalPrice: Double,
    val discountPrice: Double
)
