package com.sverbusoft.genesis_test.domain.repos.model

data class ReposModel (
    val id: Int,
    val name: String,
    val full_name: String,
    val language: String?,
    var favorite: Boolean = false,
    val url: String,
    val homepage: String?
)