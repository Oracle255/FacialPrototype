package com.capstone.prettyU.View.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.prettyU.BackEnd.Api.ApiConfig
import com.capstone.prettyU.BackEnd.Api.Response.PredictResponse
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class TestViewModel: ViewModel() {
    private val apiService = ApiConfig.getApiService()
    private val _predictResponse = MutableLiveData<PredictResponse>()
    val predictResponse: LiveData<PredictResponse>
        get() = _predictResponse


    fun predict(file: MultipartBody.Part) {
        viewModelScope.launch {
            try {
                val response = apiService.predict(file)
                _predictResponse.value = response
            } catch (e: Exception) {
                // Handle network errors or API exceptions
                Log.e("PredictViewModel", "Error predicting", e)
                // Optionally, you can handle errors with another LiveData for error messages
            }
        }
    }

}