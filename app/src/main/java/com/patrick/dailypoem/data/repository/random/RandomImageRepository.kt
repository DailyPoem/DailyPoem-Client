package com.patrick.dailypoem.data.repository.random

import com.patrick.dailypoem.data.network.RandomImageService
import com.patrick.dailypoem.util.NetworkResult
import javax.inject.Inject

class RandomImageRepository @Inject constructor(
    private val service: RandomImageService
) {
    suspend fun getRandomImage() = try {
        val result = service.getRandomImage()

        if (result.isSuccessful && result.body() != null) {
            NetworkResult.Success(result.body()!!)
        } else {
            NetworkResult.Error(result.message())
        }
    } catch (e: Exception) {
        NetworkResult.Error(e.stackTraceToString())
    }
}