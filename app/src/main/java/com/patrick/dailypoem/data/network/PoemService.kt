package com.patrick.dailypoem.data.network

import com.patrick.dailypoem.data.model.PoemData
import retrofit2.Response
import retrofit2.http.GET

interface PoemService {
    /**
     * AI가 생성한 따끈따끈하고 그럴싸해 보이는 문장을 가져옵니다.
     *
     * @return 그럴듯해 보이는 문장을 반환합니다.
     */
    @GET("epitagram")
    suspend fun getPoem(): Response<PoemData>
}
