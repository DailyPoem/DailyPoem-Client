package com.patrick.dailypoem.data.repository

import com.patrick.dailypoem.data.model.random.ImageResult
import com.patrick.dailypoem.data.network.RandomImageService
import javax.inject.Inject

class RandomImageRepository @Inject constructor(
    private val service: RandomImageService
) {
    suspend fun getRandomImage(): ImageResult = try {
        val result = service.getRandomImage()

        if (result.isSuccessful && result.body() != null) {
            result.body()!!
        } else {
            throw Exception(result.message())
        }
    } catch (e: Exception) {
        throw Exception(e.stackTraceToString())
    }
}
