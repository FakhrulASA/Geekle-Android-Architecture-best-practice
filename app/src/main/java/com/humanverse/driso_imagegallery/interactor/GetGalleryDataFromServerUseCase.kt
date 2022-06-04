package com.humanverse.driso_imagegallery.interactor

import com.humanverse.driso_imagegallery.data.image.ImageModelItem
import com.humanverse.driso_imagegallery.repository.GalleryRepositoryImpl
import javax.inject.Inject

class GetGalleryDataFromServerUseCase @Inject constructor(private val galleryRepositoryImpl: GalleryRepositoryImpl) {
    fun fetchGalleryData(success: (MutableList<ImageModelItem>) -> Unit, failure: (String) -> Unit) {
        galleryRepositoryImpl.fetchGalleryDataFromServer({
            success.invoke(it)
        }, {
            failure.invoke(it)
        })
    }
}