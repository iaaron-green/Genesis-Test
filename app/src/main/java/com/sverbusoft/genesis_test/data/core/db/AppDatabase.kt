package com.sverbusoft.genesis_test.data.core.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sverbusoft.genesis_test.App
import com.sverbusoft.genesis_test.data.features.favorite.datasource.local.FavoriteDao
import com.sverbusoft.genesis_test.data.features.repos.datasource.local.ReposDao
import com.sverbusoft.genesis_test.data.features.repos.entity.ReposEntity

@Database(
    entities = [ReposEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reposDao(): ReposDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        open fun getInstance(): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            App.getContext(),
                            AppDatabase::class.java, "github.db"
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return instance
        }
    }

}