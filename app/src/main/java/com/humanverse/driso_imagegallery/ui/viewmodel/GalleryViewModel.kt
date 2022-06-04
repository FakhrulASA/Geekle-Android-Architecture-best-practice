package com.humanverse.driso_imagegallery.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.humanverse.driso_imagegallery.data.image.ImageModelItem
import com.humanverse.driso_imagegallery.interactor.GetGalleryDataFromServerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val getGalleryDataFromServerUseCase: GetGalleryDataFromServerUseCase) :
    ViewModel() {
    var isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    fun getGalleryItem(succss: (MutableList<ImageModelItem>) -> Unit, failure: (String) -> Unit) {
        isLoading.value = true
        getGalleryDataFromServerUseCase.fetchGalleryData(
            {
                succss(it)
                isLoading.value = false
            }, {
                failure(it)
                isLoading.value = false
            }
        )
    }
}