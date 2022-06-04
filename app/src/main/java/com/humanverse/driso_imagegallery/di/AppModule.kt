package com.humanverse.driso_imagegallery.di

import com.humanverse.driso_imagegallery.remote.client.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideRetrofitClient(): Retrofit = RetrofitClient.getRetrofitInstance
}