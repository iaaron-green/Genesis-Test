package com.sverbusoft.genesis_test.data.core.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttpFactory {
    private const val DEFAULT_TIME_OUT = 30L

    private const val CONNECTION_TIMEOUT: Long =
        DEFAULT_TIME_OUT
    private const val READ_TIMEOUT: Long =
        DEFAULT_TIME_OUT
    private const val WRITE_TIMEOUT: Long =
        DEFAULT_TIME_OUT

    fun create(): OkHttpClient =
        OkHttpClient().newBuilder()
            .apply {
                connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            .build()
}