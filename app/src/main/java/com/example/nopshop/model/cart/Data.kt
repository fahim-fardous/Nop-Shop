package com.example.nopshop.model.cart

data class Data(
    val RedirectToDetailsPage: Boolean,
    val RedirectToShoppingCartPage: Boolean,
    val RedirectToWishListPage: Boolean,
    val TotalShoppingCartProducts: Int,
    val TotalWishListProducts: Int
)