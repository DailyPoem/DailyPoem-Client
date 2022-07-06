package com.patrick.dailypoem.data.network

import com.patrick.dailypoem.data.model.random.ImageResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RandomImageService {
    @GET("photos/random")
    suspend fun getRandomImage(
        @Header("Authorization") authorization: String = "Client-ID HmN9iyUFrqDORpCmKq1zZxl0fm7T_DJ0ATn7O7o32NI",
        @Query("collections") query: String = "OjKbQySOz6Q"
    ): Response<ImageResult>
}
