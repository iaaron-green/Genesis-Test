package com.sverbusoft.genesis_test.data.features.repos.dto


data class ReposResult(
    val incomplete_results: Boolean,
    val items: List<ReposResponseItem>,
    val total_count: Int
)