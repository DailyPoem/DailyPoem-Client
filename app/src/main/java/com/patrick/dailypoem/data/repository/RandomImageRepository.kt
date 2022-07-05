package com.patrick.dailypoem.data.repository

import com.patrick.dailypoem.data.network.RandomImageService
import com.patrick.dailypoem.util.NetworkResult
import javax.inject.Inject

class RandomImageRepository @Inject constructor(
    private val service: RandomImageService
) {
    suspend fun getRandomImage(): String = try {
        val result = service.getRandomImage()

        if (result.isSuccessful && result.body() != null) {
            result.body()!!.results.random().urls.small!!
        } else {
            throw Exception(result.message())
        }
    } catch (e: Exception) {
        throw Exception(e.stackTraceToString())
    }
}