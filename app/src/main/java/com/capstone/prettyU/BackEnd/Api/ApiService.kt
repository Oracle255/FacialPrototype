package com.capstone.prettyU.BackEnd.Api

import android.provider.ContactsContract.CommonDataKinds.Email
import com.capstone.prettyU.BackEnd.Api.Response.LoginResponse
import com.capstone.prettyU.BackEnd.Api.Response.PredictResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {

    @Multipart
    @POST("predict")
    suspend fun predict(
        @Part file: MultipartBody.Part,
    ): PredictResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResponse


}