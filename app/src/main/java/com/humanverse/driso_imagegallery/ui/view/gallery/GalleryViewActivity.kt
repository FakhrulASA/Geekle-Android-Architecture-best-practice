package com.humanverse.driso_imagegallery.ui.view.gallery

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.humanverse.driso_imagegallery.base.BaseActivity
import com.humanverse.driso_imagegallery.database.DataStorePreference.getCurrentAppFlavor
import com.humanverse.driso_imagegallery.database.DataStorePreference.setCurrentAppFlavor
import com.humanverse.driso_imagegallery.databinding.ActivityGalleryBinding
import com.humanverse.driso_imagegallery.interactor.GetGalleryDataFromServerUseCase
import com.humanverse.driso_imagegallery.ui.adapter.GalleryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@AndroidEntryPoint
class GalleryViewActivity : BaseActivity() {
    @Inject
    lateinit var galleryRepositoryImpl: GetGalleryDataFromServerUseCase

    private var page = 1
    private val remoteConfig = Firebase.remoteConfig
    private val appflavor = remoteConfig["APP_FLAVOR_TYPE"].asString()
    val appFlavorId: Int
        get() = if (appflavor.isBlank()) 1 else appflavor.toInt()

    @Inject
    lateinit var adapterGallery: GalleryAdapter
    private lateinit var binding: ActivityGalleryBinding
    private val vm: GalleryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoader()
        initGalleryRecyclerView()
        loadGallery()
        initAppFlavor()
        /**
         * This will be called when reach the end of recyclerview and then
         * the page value will be increased and for new data will be requested
         * after getting the data will be checked for duplicate by asyncdiffer and submitted with old
         * list and will be shown for unlimited scroll
         */
        binding.rvGalleryImage.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    page++
                    loadGallery()
                }
            }
        })
    }


    private fun initAppFlavor() {
        CoroutineScope(Dispatchers.IO).launch {
            getCurrentAppFlavor(this@GalleryViewActivity).collect {
                if (it != appFlavorId) {
                    withContext(Dispatchers.Main) {
                        updateAppFlavor()
                    }
                }
            }
        }
    }

    private fun updateAppFlavor() {
        setCurrentAppFlavor(this, appFlavorId)
    }

    private fun initGalleryRecyclerView() {
        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.rvGalleryImage.apply {
            layoutManager = gridLayoutManager
            adapter = adapterGallery
        }
    }


    private fun showLoader() {
        lifecycleScope.launch {
            vm.isLoading.collect {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.INVISIBLE

                }
            }
        }
    }

    /**
     * Submitting request for initial data and next page data as long as user keep scrolling
     */
    private fun loadGallery() {
        vm.getGalleryItem(page, this, {
            adapterGallery.addDataSet(it)
        }, {
            Log.d("MESSAGEFROMREP", it)
        })
    }
}