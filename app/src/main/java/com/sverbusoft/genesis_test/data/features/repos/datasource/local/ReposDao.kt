package com.sverbusoft.genesis_test.data.features.repos.datasource.local

import androidx.room.Dao
import com.sverbusoft.genesis_test.data.core.db.BaseDao
import com.sverbusoft.genesis_test.data.features.repos.entity.ReposEntity

@Dao
interface ReposDao:
    BaseDao<ReposEntity> {

}