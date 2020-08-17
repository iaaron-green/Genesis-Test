package com.sverbusoft.genesis_test.data.features.repos.datasource.local

import androidx.room.Dao
import androidx.room.Query
import com.sverbusoft.genesis_test.data.core.db.BaseDao
import com.sverbusoft.genesis_test.data.features.repos.entity.ReposEntity

@Dao
interface ReposDao:
    BaseDao<ReposEntity> {
    @Query("SELECT * FROM ReposEntity WHERE id = :id ORDER BY name LIMIT 1")
    fun getFavoriteRepos(id : Int) : ReposEntity

//    @Query("SELECT * FROM ReposEntity ORDER BY name")
//    fun getFavoriteRepos() : List<ReposEntity>

}