package com.patrick.dailypoem.data.repository

import com.patrick.dailypoem.data.model.Poem
import com.patrick.dailypoem.data.network.PoemService
import com.patrick.dailypoem.util.NetworkResult
import javax.inject.Inject

class PoemRepository @Inject constructor(
    private val poemService: PoemService
) {
    suspend fun getPoem(): NetworkResult<Poem> = try {
        val result = poemService.getPoem()

        if (result.isSuccessful && result.body() != null) {
            NetworkResult.Success(result.body()!!)
        } else {
            NetworkResult.Error(result.message())
        }
    } catch (e: Exception) {
        NetworkResult.Error(e.stackTraceToString())
    }
}