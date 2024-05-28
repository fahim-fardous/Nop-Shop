package com.example.nopshop.model.cart

data class AddToCartResponse(
    val Data: Data,
    val ErrorList: List<Any>,
    val Message: String
)