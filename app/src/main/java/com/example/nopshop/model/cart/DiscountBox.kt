package com.example.nopshop.model.cart

data class DiscountBox(
    val AppliedDiscountsWithCodes: List<Any>,
    val CustomProperties: CustomPropertiesXX,
    val Display: Boolean,
    val IsApplied: Boolean,
    val Messages: List<Any>
)