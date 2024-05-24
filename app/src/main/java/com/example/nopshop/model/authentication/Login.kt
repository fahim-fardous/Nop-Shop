package com.example.nopshop.model.authentication

data class Login(
    val authenticationData: authenticationData,
    val FormValues: List<Any> = emptyList(),
    val UploadPicture: UploadPicture = UploadPicture()
)