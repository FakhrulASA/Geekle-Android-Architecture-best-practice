package com.humanverse.driso_imagegallery

import com.humanverse.driso_imagegallery.data.image.ImageModel
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

class RetrofitClient {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

interface GalleryEndpoint {
    @GET()
    fun getGalleryItem(@Url url: String): Call<ImageModel>
}

class PlacesService constructor(private val retrofit: Retrofit) :
    GalleryEndpoint {
    private val endpoint by lazy { retrofit.create(GalleryEndpoint::class.java) }
    override fun getGalleryItem(url: String): Call<ImageModel> =
        endpoint.getGalleryItem(url)

}

interface TestCallback{
    fun doTest(msg : String): String
}

class GalleryAPITest {

    @Test
    fun testCallbackWithMock(){
        val callback : TestCallback = mock(TestCallback::class.java)
        verify(callback).doTest("Hello")
    }

    @Test
    fun testGalleryAPI(){
        //Get an instance of PlacesService by proiving the Retrofit instance
        val service = PlacesService(RetrofitClient().retrofit)
        //Create a new request for our API calling
        //Execute the API call
        val response = service.getGalleryItem("https://api.unsplash.com/page=1?client-id=wAkR5LSZf7uv7Fej1jLb2PLkyKBb9UzycDqZGTAK4SU").execute()
        //Check for error body
        val errorBody = response.errorBody()
        assert(errorBody == null)
        //Check for success body
        val responseWrapper = response.body()
        assert(responseWrapper != null)
        assert(response.code() == 200)
    }
}