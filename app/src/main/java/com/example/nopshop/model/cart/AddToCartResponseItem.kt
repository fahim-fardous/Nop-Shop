package com.example.nopshop.model.cart

data class AddToCartResponseItem(
    val Data: Data,
    val ErrorList: List<Any>,
    val Message: String
)