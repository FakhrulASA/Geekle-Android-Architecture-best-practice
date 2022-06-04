package com.humanverse.driso_imagegallery.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.humanverse.driso_imagegallery.base.BaseActivity
import com.humanverse.driso_imagegallery.databinding.ActivityGalleryBinding
import com.humanverse.driso_imagegallery.interactor.GetGalleryDataFromServerUseCase
import com.humanverse.driso_imagegallery.ui.adapter.GalleryAdapter
import com.humanverse.driso_imagegallery.ui.viewmodel.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GalleryViewActivity : BaseActivity() {
    @Inject
    lateinit var galleryRepositoryImpl: GetGalleryDataFromServerUseCase

    @Inject
    lateinit var adapterGallery: GalleryAdapter
    private lateinit var binding: ActivityGalleryBinding
    private val vm: GalleryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gridLayoutManager = GridLayoutManager(this, 2)
        showLoader()
        loadGallery(gridLayoutManager)

    }

    private fun showLoader(){
        lifecycleScope.launch {
            vm.isLoading.collect{
                if(it){
                    binding.progressBar.visibility= View.VISIBLE
                }else{
                    binding.progressBar.visibility= View.INVISIBLE

                }
            }
        }
    }

    private fun loadGallery(gridLayoutManager: GridLayoutManager) {
        vm.getGalleryItem(this, {
            adapterGallery.setData(it)
            binding.rvGalleryImage.apply {
                layoutManager = gridLayoutManager
                adapter = adapterGallery
                Log.d("MESSAGEFROMREP", "a")
            }
        }, {
            Log.d("MESSAGEFROMREP", it)

        })
    }
}