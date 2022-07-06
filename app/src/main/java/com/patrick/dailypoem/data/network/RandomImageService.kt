package com.patrick.dailypoem.data.network

import com.patrick.dailypoem.data.model.random.ImageResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RandomImageService {
    /**
     * https://unsplash.com/collections/OjKbQySOz6Q/portraits에서 랜덤한 한 이미지를 가져옵니다.
     *
     * @return 이미지 하나를 포함하는 응답을 반환합니다.
     */
    @GET("photos/random")
    suspend fun getRandomImage(
        @Header("Authorization") authorization: String = "Client-ID HmN9iyUFrqDORpCmKq1zZxl0fm7T_DJ0ATn7O7o32NI",
        @Query("collections") query: String = "OjKbQySOz6Q"
    ): Response<ImageResult>
}
