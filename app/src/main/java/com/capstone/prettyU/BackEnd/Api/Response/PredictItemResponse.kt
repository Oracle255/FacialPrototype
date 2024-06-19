package com.capstone.prettyU.BackEnd.Api.Response

import com.google.gson.annotations.SerializedName

data class PredictItemResponse (
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("modelResult")
    val modelResult: String,
    @field:SerializedName("modelExplanation")
    val modelExplanation: String,
    @field:SerializedName("modelSuggestion")
    val modelSuggestion: String,
    @field:SerializedName("modelConfidenceScore")
    val modelConfidenceScore: Double,
    @field:SerializedName("createdAt")
    val createdAt: String
)