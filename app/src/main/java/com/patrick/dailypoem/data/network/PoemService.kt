package com.patrick.dailypoem.data.network

import com.patrick.dailypoem.data.model.PoemData
import retrofit2.Response
import retrofit2.http.GET

interface PoemService {
    /**
     * 서버에 Poem를 요청
     *
     * GET의 엔드포인트는 실제 서버 완성 시 수정해야 합니다
     *
     * @return 생성된 Poem을 반환합니다.
     */
    // TODO: 서버 완성 시 엔드포인트 수정 필요
    @GET("epitagram")
    suspend fun getPoem(): Response<PoemData>
}