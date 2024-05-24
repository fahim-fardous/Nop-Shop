package com.example.nopshop.network.api

import com.example.nopshop.model.authentication.Login
import com.example.nopshop.model.authentication.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {
    @POST("customer/login")
    suspend fun userLogin(@Body request: Login): Response<LoginResponse>
}