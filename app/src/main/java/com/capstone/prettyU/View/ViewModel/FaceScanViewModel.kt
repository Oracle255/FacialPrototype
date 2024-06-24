package com.capstone.prettyU.View.ViewModel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.prettyU.BackEnd.Api.Response.MessageResponse
import com.capstone.prettyU.BackEnd.Utilities.LocalPreference
import com.capstone.prettyU.BackEnd.Utilities.Utils
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.prettyU.BackEnd.Api.ApiConfig
import com.capstone.prettyU.BackEnd.Api.Response.PredictItemResponse
import com.capstone.prettyU.BackEnd.Api.Response.PredictResponse
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class FaceScanViewModel(localPreference: LocalPreference): ViewModel() {
    private val utils = Utils()

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val _predictResult = MutableLiveData<String>()
    val predictResult: LiveData<String> = _predictResult

    private val _listResult = MutableLiveData<PredictItemResponse?>()
    val listResul: MutableLiveData<PredictItemResponse?> get() = _listResult

    private val _modelResult = MutableLiveData<String?>()
    val modelResult: MutableLiveData<String?> = _modelResult

    private val _modelExplanation = MutableLiveData<String?>()
    val modelExplanation: MutableLiveData<String?> = _modelExplanation

    private val _modelSuggestion = MutableLiveData<String?>()
    val modelSuggestion: MutableLiveData<String?> = _modelSuggestion

    private val _modelConfidenceScore = MutableLiveData<Double?>()
    val modelConfidenceScore: MutableLiveData<Double?> = _modelConfidenceScore

    private val _createdAt = MutableLiveData<String?>()
    val createdAt: MutableLiveData<String?> = _createdAt

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: MutableLiveData<String?> = _errorMessage

    fun uploadImage(context: Context, imageUri: Uri?) {
        imageUri?.let {  uri ->
            val imageFile = utils.uriToFile(uri, context).reduceFileImage()
            val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData(
                "image",
                imageFile.name,
                requestImageFile
            )

            viewModelScope.launch{
                try {
                    val apiService = ApiConfig.getApiService()
                    val response =
                        apiService.predict(multipartBody)
                    val listResponse = response.data
                    //val message = successResponse.message
                    Toast.makeText(context,response.message,Toast.LENGTH_SHORT)
                    if (listResponse != null) {
                        _listResult.postValue(listResponse)
                        _modelResult.postValue(listResponse.modelResult)
                        _modelExplanation.postValue(listResponse.modelExplanation)
                        _modelSuggestion.postValue(listResponse.modelSuggestion)
                        _modelConfidenceScore.postValue(listResponse.modelConfidenceScore)
                        _createdAt.postValue(listResponse.createdAt)
                        _status.postValue(response.status)
                    }
                    _predictResult.postValue(response.toString())
                    //_predictResult.postValue(successResponse.listPredict.toString())


                } catch (e: retrofit2.HttpException) {
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

    private fun File.reduceFileImage(): File {
        val maxSize = 1000000
        val file = this
        val bitmap = BitmapFactory.decodeFile(file.path)
        var compressQuality = 100
        var streamLength: Int
        do {
            val bmpStream = ByteArrayOutputStream()
            bitmap?.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)
            val bmpPicByteArray = bmpStream.toByteArray()
            streamLength = bmpPicByteArray.size
            compressQuality -= 5
        } while (streamLength > maxSize)
        bitmap?.compress(Bitmap.CompressFormat.JPEG, compressQuality, FileOutputStream(file))
        return file
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }

//    private fun createCustomTempFile(context: Context): File {
//        val filesDir = context.externalCacheDir
//        return File.createTempFile(timeStamp, ".jpg", filesDir)
//    }
//
//    private fun uriToFile(imageUri: Uri, context: Context): File {
//        val myFile = createCustomTempFile(context)
//        val inputStream = context.contentResolver.openInputStream(imageUri) as InputStream
//        val outputStream = FileOutputStream(myFile)
//        val buffer = ByteArray(1024)
//        var length: Int
//        while (inputStream.read(buffer).also { length = it } > 0) outputStream.write(buffer, 0, length)
//        outputStream.close()
//        inputStream.close()
//        return myFile
//    }

}