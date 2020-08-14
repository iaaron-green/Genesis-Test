package com.sverbusoft.genesis_test.data.features.repos.api

import com.sverbusoft.genesis_test.data.features.repos.model.ReposResponseItem
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface ReposApi {
    @POST("/search/repositories")
    fun search(@Query("q") q: String): Single<List<ReposResponseItem>>
}