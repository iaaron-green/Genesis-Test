package com.sverbusoft.genesis_test.data.features.repos.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sverbusoft.genesis_test.data.core.db.AppDatabase
import com.sverbusoft.genesis_test.data.core.network.ApiConfig
import com.sverbusoft.genesis_test.data.core.network.OkHttpFactory
import com.sverbusoft.genesis_test.data.core.network.RetrofitFactory
import com.sverbusoft.genesis_test.data.features.repos.api.MockedReposApi
import com.sverbusoft.genesis_test.data.features.repos.api.ReposApi
import com.sverbusoft.genesis_test.data.features.repos.datasource.local.ReposDao
import com.sverbusoft.genesis_test.data.features.repos.datasource.paging.ReposPageDataSourceFactory
import com.sverbusoft.genesis_test.data.features.repos.datasource.remote.ReposRemoteDataSource
import com.sverbusoft.genesis_test.data.features.repos.mapper.ReposModelToEntityMapper
import com.sverbusoft.genesis_test.domain.repos.model.ReposModel
import com.sverbusoft.genesis_test.domain.repos.repository.ReposRepository
import io.reactivex.disposables.CompositeDisposable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ReposRepositoryImpl(compositeDisposable: CompositeDisposable): ReposRepository {
    private var remoteDataSource: ReposRemoteDataSource
    private var localDataSource: ReposDao
    private var dataSourceFactory: ReposPageDataSourceFactory

    init {
        val api = when (ApiConfig.USE_MOCKED_REPOS_API) {
            false -> RetrofitFactory.create(
                OkHttpFactory.create(),
                RxJava2CallAdapterFactory.create(),
                GsonConverterFactory.create(),
                ApiConfig.BASE_URL
            ).create(ReposApi::class.java)
            else -> MockedReposApi()
        }

        remoteDataSource =
            ReposRemoteDataSource(
                api
            )

        localDataSource = AppDatabase.getInstance()!!.reposDao()

        dataSourceFactory = ReposPageDataSourceFactory(remoteDataSource, localDataSource,"", compositeDisposable)
    }

    override fun searchRepos(name: String) {
        Log.d("MyTag searchRepos", "name: $name")
        dataSourceFactory.name = name
        dataSourceFactory.refresh()
    }

    override fun getPagedList(): LiveData<PagedList<ReposModel>> {
        return LivePagedListBuilder(
            dataSourceFactory,
            ReposPageDataSourceFactory.pagedListConfig()
        ).build()
    }

    override fun addToFavorite(item: ReposModel) =
        localDataSource.insert(ReposModelToEntityMapper().mapToObject(item))


}

