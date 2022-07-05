package com.patrick.dailypoem.data.repository

import com.patrick.dailypoem.data.model.Poem
import com.patrick.dailypoem.util.NetworkResult
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val poemRepository: PoemRepository,
    private val randomImageRepository: RandomImageRepository
) {
    private val randomNameRepository = RandomNameRepository()

    suspend fun getPoem(): NetworkResult<Poem> = try {
        val imageUrl = randomImageRepository.getRandomImage()
        val poem = poemRepository.getPoem()
        val name = randomNameRepository.getRandomName()

        NetworkResult.Success(
            Poem(
                imageUrl = imageUrl,
                poem = poem,
                teller = name
            )
        )
    } catch (e: Exception) {
        NetworkResult.Error(e.message)
    }
}