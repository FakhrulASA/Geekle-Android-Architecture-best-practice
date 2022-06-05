package com.humanverse.driso_imagegallery.ui.view.gallery

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.humanverse.driso_imagegallery.data.image.ImageModelItem
import com.humanverse.driso_imagegallery.interactor.GetGalleryDataFromServerUseCase
import com.humanverse.driso_imagegallery.util.isNetworkAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getGalleryDataFromServerUseCase: GetGalleryDataFromServerUseCase
) :
    ViewModel() {
    var isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    @RequiresApi(Build.VERSION_CODES.M)
    fun getGalleryItem(
        page:Int,
        context: Context,
        success: (MutableList<ImageModelItem>) -> Unit,
        failure: (String) -> Unit
    ) {
        isLoading.value = true
        if (isNetworkAvailable(context)) {
            getGalleryDataFromServerUseCase.fetchGalleryData(
                page,
                {
                    success(it)
                    isLoading.value = false
                }, {
                    failure(it)
                    isLoading.value = false
                }
            )
        } else {
            failure("No internet available!")
        }

    }
}