package com.humanverse.driso_imagegallery.repository

import android.util.Log
import com.humanverse.driso_imagegallery.data.image.ImageModel
import com.humanverse.driso_imagegallery.remote.endpoint.GalleryAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(private val galleryAPI: GalleryAPI) :
    GalleryRepository {
    override fun fetchGalleryDataFromServer() {
        val galleryCall =
            galleryAPI.fetchImageFromServer("wAkR5LSZf7uv7Fej1jLb2PLkyKBb9UzycDqZGTAK4SU", null)
        galleryCall.enqueue(object : Callback<ImageModel> {
            override fun onResponse(call: Call<ImageModel>, response: Response<ImageModel>) {
                var imageData = response.body()

                Log.d("MESSAGEFROMREP", imageData?.size.toString())

            }

            override fun onFailure(call: Call<ImageModel>, t: Throwable) {
                Log.d("MESSAGEFROMREP", t.message.toString())

            }

        })
    }
}