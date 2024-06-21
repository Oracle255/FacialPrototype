package com.capstone.prettyU.View.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.prettyU.BackEnd.Api.ApiConfig
import com.capstone.prettyU.BackEnd.Api.Response.MessageResponse
import com.capstone.prettyU.BackEnd.Utilities.LocalPreference
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(private val localPreference: LocalPreference): ViewModel() {

    private val _loginResult = MutableLiveData<String>()
    val loginResult: LiveData<String> = _loginResult

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: MutableLiveData<String?> = _errorMessage

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val apiService = ApiConfig.getApiService()
                val response = apiService.login(username, password)
                val message = response.message
                val state = response.error
                response.loginResult?.token?.let {
                    localPreference.addToken(it)
                }
                response.loginResult?.email?.let {
                    localPreference.addName(it)
                }
                response.loginResult?.userId?.let {
                    localPreference.addEmail(it)
                }
                _loginResult.postValue(message!!)
//                if (!state!!) {
//
//                }
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

    fun clearErrorMessage() {
        _errorMessage.value = null
    }

}