package com.humanverse.driso_imagegallery.ui.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.humanverse.driso_imagegallery.R
import com.humanverse.driso_imagegallery.base.BaseActivity
import com.humanverse.driso_imagegallery.interactor.GetGalleryDataFromServerUseCase
import com.humanverse.driso_imagegallery.ui.viewmodel.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GalleryViewActivity : BaseActivity() {
    @Inject
    lateinit var galleryRepositoryImpl: GetGalleryDataFromServerUseCase
    private val vm: GalleryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        vm.getGalleryItem({
            Log.d("MESSAGEFROMREP", it[0].urls.full)
        }, {
            Log.d("MESSAGEFROMREP", it)
        })

    }
}