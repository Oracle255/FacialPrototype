package com.capstone.prettyU.View.ViewModel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.prettyU.BackEnd.Api.Response.MessageResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(private val sharedPreferences: SharedPreferences): ViewModel() {

    private val _loginResult = MutableLiveData<String>()
    val loginResult: LiveData<String> = _loginResult

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: MutableLiveData<String?> = _errorMessage

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {

            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, MessageResponse::class.java)
                _errorMessage.postValue(errorResponse.message)
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.postValue("Unknown Error")
            }
        }
    }

}