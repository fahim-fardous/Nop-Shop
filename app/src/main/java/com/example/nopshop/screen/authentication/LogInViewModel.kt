package com.example.nopshop.screen.authentication

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopshop.model.authentication.Data
import com.example.nopshop.model.authentication.Login
import com.example.nopshop.model.authentication.LoginResponse
import com.example.nopshop.network.ApiClient
import com.example.nopshop.network.api.AuthenticationApi
import com.example.nopshop.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val repository: LoginRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _response: MutableLiveData<LoginResponse> by lazy {
        MutableLiveData<LoginResponse>()
    }
    val response: LiveData<LoginResponse>
        get() = _response

    private val _showMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()

    }

    val showMessage: LiveData<String>
        get() = _showMessage


    private fun isValid(userEmail: String, userPassword: String): Boolean {
        if (userEmail.isEmpty() || userPassword.isEmpty()) {
            _showMessage.value = "Please fill all fields"
            return false
        }
        return true
    }

    fun postLogin(userEmail: String, userPassword: String) = viewModelScope.launch {
        if (!isValid(userEmail, userPassword)) return@launch
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
        } else {
            _showMessage.value = "Incorrect email or password"
        }
    }

}