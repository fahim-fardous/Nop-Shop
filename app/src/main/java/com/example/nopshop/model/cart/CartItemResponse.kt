package com.example.nopshop.model.cart

data class CartItemResponse(
    val Data: DataX,
    val ErrorList: List<Any>,
    val Message: Any
)