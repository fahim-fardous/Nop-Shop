package com.example.nopshop.model.authentication

data class Data(
    val CheckoutAsGuest: Boolean = false,
    val DisplayCaptcha: Boolean=false,
    val Email: String,
    val Password: String,
    val RegistrationType: Int = 2,
    val RememberMe: Boolean = false,
    val UsernamesEnabled: Boolean = false
)