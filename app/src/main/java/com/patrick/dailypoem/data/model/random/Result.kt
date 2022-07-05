package com.patrick.dailypoem.data.model.random


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("urls")
    val urls: Urls,
)