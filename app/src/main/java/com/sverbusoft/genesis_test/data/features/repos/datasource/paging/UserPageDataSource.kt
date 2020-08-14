package com.example.swivltestassigment.data.datasource.paging

import android.util.Log
import androidx.paging.PositionalDataSource
import com.example.swivltestassigment.data.datasource.remote.UserApiDataSource
import com.example.swivltestassigment.data.model.UserModel
import com.example.swivltestassigment.data.network.Result
import com.example.swivltestassigment.data.network.mapper.UserApiMapper
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserPageDataSource(
    private val dataSource: UserApiDataSource
): PositionalDataSource<UserModel>()  {
    var id: Int = 0;

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<UserModel>) {
        fetchData(id) {
            id = it[it.lastIndex].id
            callback.onResult(it, 0)
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<UserModel>) {
        fetchData(id) {
            id = it[it.lastIndex].id
            callback.onResult(it)
        }
    }

    private fun fetchData(pageSize: Int, callback: (List<UserModel>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch(getJobErrorHandler()) {

            val response = dataSource.getUsersList(pageSize)
            if (response.status == Result.Status.SUCCESS) {
                val results = response.data
                if (results != null) {
                    callback(UserApiMapper().listFromEntity(results));
                };
            } else if (response.status == Result.Status.ERROR) {
                postError(response.message!!)
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        Log.e("TAG", "An error happened: $message")
    }
}