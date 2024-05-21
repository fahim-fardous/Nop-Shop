package com.example.nopshop.screen.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopshop.model.authentication.Data
import com.example.nopshop.model.authentication.DataX
import com.example.nopshop.model.authentication.Login
import com.example.nopshop.network.ApiClient
import com.example.nopshop.network.api.AuthenticationApi
import com.example.nopshop.repository.LoginRepository
import kotlinx.coroutines.launch

class LogInViewModel : ViewModel() {
    private val _response: MutableLiveData<DataX> by lazy {
        MutableLiveData<DataX>()
    }
    val response: LiveData<DataX>
        get() = _response

    private val apiClient = ApiClient.getRetrofit().create(AuthenticationApi::class.java)
    private val repository = LoginRepository(apiClient)

    fun postLogin(userEmail: String, userPassword: String) = viewModelScope.launch {
        val response = repository.userLogin(
            Login(
                Data = Data(
                    Email = userEmail,
                    Password = userPassword,
                )
            )
        )
        if (response.isSuccessful) {
            _response.value = response.body()
        }
    }

}