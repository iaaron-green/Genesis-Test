package com.sverbusoft.genesis_test.data.features.repos.dto

data class ReposResponseItem(
    val full_name: String,
    val homepage: String?,
    val id: Int,
    val name: String,
    val url: String,
    val language: String
)