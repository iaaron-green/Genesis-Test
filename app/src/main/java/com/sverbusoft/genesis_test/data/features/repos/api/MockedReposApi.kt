package com.sverbusoft.genesis_test.data.features.repos.api

import com.sverbusoft.genesis_test.data.features.repos.dto.ReposResponseItem
import com.sverbusoft.genesis_test.data.features.repos.dto.ReposResult
import io.reactivex.Single
import io.reactivex.internal.operators.single.SingleJust

class MockedReposApi : ReposApi {
    override fun search(q: String, page: Int, per_page: Int): Single<ReposResult> = SingleJust<ReposResult>(
        ReposResult(true, listOf(
            ReposResponseItem(
                "ded/qwery",
                "",
                1470094,
                "qwery",
                "https://api.github.com/repos/ded/qwery"
            ),
            ReposResponseItem(
                "ded/ewq",
                "",
                1470095,
                "ewq",
                "https://api.github.com/repos/ded/ewq"
            )
        ), 0)

    )


}