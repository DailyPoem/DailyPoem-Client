package com.patrick.dailypoem.data.model

import com.google.gson.annotations.SerializedName

data class PoemData(
    @SerializedName("code")
    val code: String?,
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("status")
    val status: String?
)