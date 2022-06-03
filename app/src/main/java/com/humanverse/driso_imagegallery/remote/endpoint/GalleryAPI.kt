package com.humanverse.driso_imagegallery.remote.endpoint

import com.humanverse.driso_imagegallery.data.image.ImageModel
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject

class GalleryAPI @Inject constructor(private val retrofit: Retrofit) : GalleryAPIEndpointInterface {
    val api: GalleryAPIEndpointInterface by lazy {
        retrofit.create(GalleryAPIEndpointInterface::class.java)
    }
    override fun fetchImageFromServer(cliendId: String, page: Int?): Call<ImageModel> =
        api.fetchImageFromServer(cliendId, page)
}