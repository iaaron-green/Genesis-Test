package com.sverbusoft.genesis_test.data.features.favorite.datasource.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.sverbusoft.genesis_test.data.core.db.BaseDao
import com.sverbusoft.genesis_test.data.features.repos.entity.ReposEntity

@Dao
interface FavoriteDao: BaseDao<ReposEntity> {

    @Query("SELECT * FROM ReposEntity WHERE name LIKE :name LIMIT :end OFFSET :start")
    fun getFavoriteRepos(start: Int, end: Int, name : String) : List<ReposEntity>

//    @Query("SELECT * FROM ReposEntity")
//    fun getFavoriteRepos(): List<ReposEntity>
}