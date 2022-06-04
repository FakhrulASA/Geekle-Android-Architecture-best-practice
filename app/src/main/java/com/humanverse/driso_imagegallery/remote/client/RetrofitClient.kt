package com.humanverse.driso_imagegallery.remote.client

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.humanverse.driso_imagegallery.util.BaseUrl.getBaseUrl
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object RetrofitClient {

//    private val cacheSize = 10 * 1024 * 1024 // 10 MB
//    private val httpCacheDirectory = File(context.cacheDir, "http-cache")
//    private val cache = Cache(httpCacheDirectory, cacheSize.toLong())

    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val myHttpClient: OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true).addInterceptor(loggingInterceptor).build()


    private val gson: Gson by lazy {
        GsonBuilder()
            .setLenient()
            .serializeNulls()
            .create()
    }


    private var retrofit: Retrofit = getNewInstance(getBaseUrl())

    private fun getNewInstance(url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(myHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


    val getRetrofitInstance: Retrofit
        get() = retrofit


}