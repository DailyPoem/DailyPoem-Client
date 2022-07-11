package com.patrick.dailypoem.data.repository

import com.patrick.dailypoem.data.model.Poem
import com.patrick.dailypoem.util.NetworkResult
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val poemRepository: PoemRepository,
    private val randomImageRepository: RandomImageRepository
) {
    private val randomNameRepository = RandomNameRepository()

    suspend fun getPoem(): NetworkResult<Poem> {
        val imageUrl = try {
            randomImageRepository.getRandomImage()
        } catch (e: Exception) {
            null
        }
        val poem = try {
            poemRepository.getPoem()
        } catch (e: Exception) {
            return NetworkResult.Error(e.stackTraceToString())
        }
        val name = randomNameRepository.getRandomName()

        return NetworkResult.Success(
            Poem(
                image = imageUrl,
                poem = poem,
                teller = name
            )
        )
    }
}
