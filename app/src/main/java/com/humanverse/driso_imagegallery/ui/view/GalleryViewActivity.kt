package com.humanverse.driso_imagegallery.ui.view

import android.os.Bundle
import com.humanverse.driso_imagegallery.R
import com.humanverse.driso_imagegallery.base.BaseActivity
import com.humanverse.driso_imagegallery.repository.GalleryRepositoryImpl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GalleryViewActivity : BaseActivity() {
    @Inject
    lateinit var galleryRepositoryImpl: GalleryRepositoryImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        galleryRepositoryImpl.fetchGalleryDataFromServer()
    }
}