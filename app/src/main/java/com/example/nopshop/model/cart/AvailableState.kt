package com.example.nopshop.model.cart

data class AvailableState(
    val Disabled: Boolean,
    val Group: Any,
    val Selected: Boolean,
    val Text: String,
    val Value: String
)