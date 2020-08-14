package com.sverbusoft.genesis_test.data.features.repos.repository

import com.sverbusoft.genesis_test.data.features.repos.api.MockedReposApi
import com.sverbusoft.genesis_test.data.features.repos.api.ReposApi
import com.sverbusoft.genesis_test.data.features.repos.datasource.ReposRemoteDataSource
import com.sverbusoft.genesis_test.data.features.repos.model.ReposResponseItem
import com.sverbusoft.genesis_test.data.network.core.ApiConfig
import com.sverbusoft.genesis_test.data.network.core.OkHttpFactory
import com.sverbusoft.genesis_test.data.network.core.RetrofitFactory
import io.reactivex.Single
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ReposRepository {
    private lateinit var remoteDataSource: ReposRemoteDataSource;

    init {
        val api = when(ApiConfig.USE_MOCKED_REPOS_API){
            true -> RetrofitFactory.create(
                OkHttpFactory.create(),
                RxJava2CallAdapterFactory.create(),
                GsonConverterFactory.create(),
                ApiConfig.BASE_URL
            ).create(ReposApi::class.java);
            else -> MockedReposApi()
        }
        remoteDataSource = ReposRemoteDataSource(api);
    }

    fun searchRepos(name: String): Single<List<ReposResponseItem>> =
        remoteDataSource.getUserRepos(name)
}