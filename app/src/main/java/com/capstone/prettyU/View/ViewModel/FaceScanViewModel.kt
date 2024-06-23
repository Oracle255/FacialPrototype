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
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class FaceScanViewModel(localPreference: LocalPreference): ViewModel() {
    private val utils = Utils()

    private val _predictResult = MutableLiveData<String>()
    val predictResult: LiveData<String> = _predictResult

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
                    val status = response.status
                    Toast.makeText(context,response.message,Toast.LENGTH_SHORT)

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