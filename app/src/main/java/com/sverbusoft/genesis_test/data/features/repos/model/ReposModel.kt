package com.sverbusoft.genesis_test.data.features.repos.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class ReposModel (
    val id: Int,
    val name: String,
    val full_name: String,
    val favorite: Boolean = false,
    val url: String,
    val homepage: String?
)