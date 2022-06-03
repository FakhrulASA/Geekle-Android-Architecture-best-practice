package com.humanverse.driso_imagegallery.di

import com.humanverse.driso_imagegallery.remote.endpoint.GalleryAPI
import com.humanverse.driso_imagegallery.repository.GalleryRepository
import com.humanverse.driso_imagegallery.repository.GalleryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun provideNoteRepository(galleryAPI: GalleryAPI): GalleryRepository =
        GalleryRepositoryImpl(galleryAPI)
}