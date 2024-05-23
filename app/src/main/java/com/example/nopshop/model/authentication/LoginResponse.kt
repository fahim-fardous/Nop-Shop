package com.example.nopshop.model.authentication

data class LoginResponse(
    val Data: DataX,
    val ErrorList: List<Any>,
    val Message: Any
)