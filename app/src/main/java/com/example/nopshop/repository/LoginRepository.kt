package com.example.nopshop.repository

import com.example.nopshop.model.authentication.Login
import com.example.nopshop.model.authentication.LoginResponse
import com.example.nopshop.network.api.AuthenticationApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginRepository(private val api: AuthenticationApi) {

    suspend fun userLogin(logInRequest: Login): Response<LoginResponse> = withContext(Dispatchers.IO) {
        return@withContext api.userLogin(logInRequest)
    }
}