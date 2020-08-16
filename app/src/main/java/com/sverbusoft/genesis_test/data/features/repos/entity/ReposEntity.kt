package com.sverbusoft.genesis_test.data.features.repos.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReposEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "full_name") val full_name: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "homepage") val homepage: String?
)