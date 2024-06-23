package com.capstone.prettyU.BackEnd.Api.Response

import com.google.gson.annotations.SerializedName

data class PredictResponse(
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("data")
    val data: PredictItemResponse? = null
)
