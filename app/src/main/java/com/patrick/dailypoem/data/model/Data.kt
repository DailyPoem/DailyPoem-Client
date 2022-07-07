package com.patrick.dailypoem.data.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("epitagram")
    val epitagram: String
)
