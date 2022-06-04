package com.humanverse.driso_imagegallery.repository

import com.humanverse.driso_imagegallery.data.image.ImageModel
import com.humanverse.driso_imagegallery.data.image.ImageModelItem
import com.humanverse.driso_imagegallery.remote.endpoint.GalleryAPI
import com.humanverse.driso_imagegallery.util.getFailure
import com.humanverse.driso_imagegallery.util.isNetworkAvailable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(private val galleryAPI: GalleryAPI) :
    GalleryRepository {
    override fun fetchGalleryDataFromServer(
        page:Int,
        onSuccess: (MutableList<ImageModelItem>) -> Unit,
        onFailed: (String) -> Unit
    ) {
        val galleryCall =
            galleryAPI.fetchImageFromServer("wAkR5LSZf7uv7Fej1jLb2PLkyKBb9UzycDqZGTAK4SU", page)
        galleryCall.enqueue(object : Callback<ImageModel> {
            override fun onResponse(call: Call<ImageModel>, response: Response<ImageModel>) {
                if (response.isSuccessful) {
                    onSuccess.invoke(response.body() as MutableList<ImageModelItem>)

                } else {
                    onFailed.invoke(getFailure(response.code()))
                }
            }

            override fun onFailure(call: Call<ImageModel>, t: Throwable) {
                onFailed.invoke(t.message!!)
            }

        })

    }
}