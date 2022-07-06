package com.patrick.dailypoem.data.network

import com.patrick.dailypoem.data.model.PoemData
import retrofit2.Response
import retrofit2.http.GET

interface PoemService {
    @GET("epitagram")
    suspend fun getPoem(): Response<PoemData>
}
