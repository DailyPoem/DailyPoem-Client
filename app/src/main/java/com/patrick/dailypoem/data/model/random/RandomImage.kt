package com.patrick.dailypoem.data.model.random


import com.google.gson.annotations.SerializedName

data class RandomImage(
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?
)