package com.example.nopshop.model.authentication

data class Login(
    val Data: Data,
    val FormValues: List<Any> = emptyList(),
    val UploadPicture: UploadPicture = UploadPicture()
)