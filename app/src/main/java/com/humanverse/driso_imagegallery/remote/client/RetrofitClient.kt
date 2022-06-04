package com.humanverse.driso_imagegallery.remote.client

import android.content.Context
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitClient @Inject constructor(context: Context) {
    val cacheSize = 10 * 1024 * 1024 // 10 MB
    val httpCacheDirectory = File(context.cacheDir, "http-cache")
    val cache = Cache(httpCacheDirectory, cacheSize.toLong())
    private val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()

    val httpClient by lazy {
        OkHttpClient.Builder()
            .cache(cache)
            .addNetworkInterceptor(networkCacheInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private val networkCacheInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())

        val cacheControl = CacheControl.Builder()
            .maxAge(1, TimeUnit.MINUTES)
            .build()

        response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }


}