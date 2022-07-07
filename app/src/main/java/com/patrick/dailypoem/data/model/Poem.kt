package com.patrick.dailypoem.data.model

import com.patrick.dailypoem.data.model.random.ImageResult

data class Poem(
    val image: ImageResult,
    val poem: String,
    val teller: String
)
