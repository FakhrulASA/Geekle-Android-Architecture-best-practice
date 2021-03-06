package com.humanverse.driso_imagegallery.repository

import com.humanverse.driso_imagegallery.data.image.ImageModelItem

interface GalleryRepository {
    fun fetchGalleryDataFromServer(
        page:Int,
        onSuccess: (MutableList<ImageModelItem>) -> Unit,
        onFailed: (String) -> Unit
    )
}