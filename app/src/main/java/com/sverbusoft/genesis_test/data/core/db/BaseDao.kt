package com.sverbusoft.genesis_test.data.core.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import io.reactivex.Completable

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: T)

    @Update
    fun update(data: T)

    @Delete
    fun delete(data: T)
}