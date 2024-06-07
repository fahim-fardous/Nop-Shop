package com.example.nopshop.screen.authentication.account

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopshop.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private val _success: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val success: LiveData<Boolean>
        get() = _success

    fun logOut() = viewModelScope.launch {
        sharedPreferences.edit().clear().apply()
        Constants.TOKEN = null
        _success.value = true
    }
}