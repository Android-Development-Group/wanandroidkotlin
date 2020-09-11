package com.yicooll.wanandroidkotlin.api_service

import com.yicooll.wanandroidkotlin.entity.ModelSearch
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url

interface SearchService {

    @POST
    @FormUrlEncoded
    fun searchArtical(@Url url: String, @Field("k") word: String): Observable<ModelSearch>
}