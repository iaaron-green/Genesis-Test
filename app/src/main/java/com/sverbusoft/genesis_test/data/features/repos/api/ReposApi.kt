package com.sverbusoft.genesis_test.data.features.repos.api

import com.sverbusoft.genesis_test.data.features.repos.model.ReposResponseItem
import com.sverbusoft.genesis_test.data.features.repos.model.ReposResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ReposApi {
    @GET("search/repositories")
    fun search(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Single<ReposResult>
}