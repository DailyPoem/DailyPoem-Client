package com.patrick.dailypoem.data.model.random

import com.google.gson.annotations.SerializedName

data class Urls(
    @SerializedName("raw")
    val raw: String,
    @SerializedName("full")
    val full: String,
    @SerializedName("regular")
    val regular: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("thumb")
    val thumb: String,
    @SerializedName("small_s3")
    val smallS3: String
)
