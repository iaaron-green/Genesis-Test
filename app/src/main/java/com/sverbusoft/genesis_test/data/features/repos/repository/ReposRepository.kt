package com.sverbusoft.genesis_test.data.features.repos.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sverbusoft.genesis_test.data.features.repos.api.MockedReposApi
import com.sverbusoft.genesis_test.data.features.repos.api.ReposApi
import com.sverbusoft.genesis_test.data.features.repos.datasource.paging.ReposPageDataSource
import com.sverbusoft.genesis_test.data.features.repos.datasource.paging.ReposPageDataSourceFactory
import com.sverbusoft.genesis_test.data.features.repos.datasource.remote.ReposRemoteDataSource
import com.sverbusoft.genesis_test.data.features.repos.model.ReposResponseItem
import com.sverbusoft.genesis_test.data.network.core.ApiConfig
import com.sverbusoft.genesis_test.data.network.core.OkHttpFactory
import com.sverbusoft.genesis_test.data.network.core.RetrofitFactory
import io.reactivex.Single
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
class ReposRepository {
    private var remoteDataSource: ReposRemoteDataSource;
    private var dataSourceFactory: ReposPageDataSourceFactory;

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

        remoteDataSource =
            ReposRemoteDataSource(
                api
            );

        dataSourceFactory = ReposPageDataSourceFactory(remoteDataSource, "")
    }

    fun searchRepos(name: String) {
        Log.d("MyTag searchRepos", "name: $name")
        dataSourceFactory.name = name
        dataSourceFactory.refresh()
    }

    fun getPagedList(): LiveData<PagedList<ReposResponseItem>>{
        return LivePagedListBuilder(
            dataSourceFactory,
            ReposPageDataSourceFactory.pagedListConfig()
        ).build()
    }
}

