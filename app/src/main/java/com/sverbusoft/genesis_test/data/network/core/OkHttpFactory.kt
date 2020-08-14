package com.sverbusoft.genesis_test.data.network.core

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object OkHttpFactory {
    private const val DEFAULT_TIME_OUT = 30L

    private const val CONNECTION_TIMEOUT: Long = DEFAULT_TIME_OUT
    private const val READ_TIMEOUT: Long = DEFAULT_TIME_OUT
    private const val WRITE_TIMEOUT: Long = DEFAULT_TIME_OUT

    @Suppress("LongMethod")
    fun create(): OkHttpClient =
        OkHttpClient().newBuilder()
            .apply {
                connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            }
            .build()
}