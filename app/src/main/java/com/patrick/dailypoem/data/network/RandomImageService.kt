package com.patrick.dailypoem.data.network

import com.patrick.dailypoem.data.model.random.RandomImage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RandomImageService {
    @GET("/search/photos")
    suspend fun getRandomImage(
        @Header("Authorization") authorization: String = "Client-ID HmN9iyUFrqDORpCmKq1zZxl0fm7T_DJ0ATn7O7o32NI",
        @Query("query") query: String = "historical portrait",
        @Query("color") color: String = "black_and_white"
    ): Response<RandomImage>
}