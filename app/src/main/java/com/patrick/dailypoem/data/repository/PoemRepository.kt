package com.patrick.dailypoem.data.repository

import com.patrick.dailypoem.data.network.PoemService
import javax.inject.Inject

class PoemRepository @Inject constructor(
    private val poemService: PoemService
) {
    suspend fun getPoem(): String = try {
        val result = poemService.getPoem()

        if (result.isSuccessful && result.body() != null) {
            result.body()!!.data.epitagram
        } else {
            throw Exception(result.body()?.code ?: result.message())
        }
    } catch (e: Exception) {
        throw Exception(e.stackTraceToString())
    }
}