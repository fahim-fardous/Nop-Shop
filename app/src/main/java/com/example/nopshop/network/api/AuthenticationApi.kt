package com.example.nopshop.network.api

import com.example.nopshop.model.authentication.Data
import com.example.nopshop.model.authentication.DataX
import com.example.nopshop.model.authentication.Login
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {
    @POST("customer/login")
    suspend fun userLogin(@Body request: Login): Response<DataX>
}