package com.humanverse.driso_imagegallery.remote.endpoint

import com.humanverse.driso_imagegallery.data.image.ImageModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GalleryAPIEndpointInterface {
    @GET("photos")
    fun fetchImageFromServer(
        @Query("client_id") cliendId: String,
        @Query("page") page: Int?
    ): Call<ImageModel>
}