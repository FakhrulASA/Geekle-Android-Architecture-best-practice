package com.humanverse.driso_imagegallery.ui.adapter

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.humanverse.driso_imagegallery.data.image.ImageModelItem
import com.humanverse.driso_imagegallery.databinding.ItemGalleryBinding
import com.humanverse.driso_imagegallery.ui.view.PhotoViewActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GalleryAdapter @Inject constructor(
    @ApplicationContext val context: Context
) :
    RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {
    class GalleryViewHolder(private val binding: ItemGalleryBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(list: List<ImageModelItem>, position: Int) {
            Glide
                .with(context)
                .load(list[position].urls.small)
                .centerCrop()
                .into(binding.imageView)

            binding.imageView.setOnClickListener {
                val intent = Intent(context, PhotoViewActivity::class.java)
                intent.putExtra("URL", list[position].urls.regular)
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }

    }

    private var weatherList: List<ImageModelItem> = mutableListOf()
    override fun getItemCount(): Int = weatherList.size

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bindView(weatherList, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder =
        GalleryViewHolder(
            ItemGalleryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            context
        )

    fun setData(weatherList: List<ImageModelItem>) {
        this.weatherList = weatherList
        notifyDataSetChanged()
    }

}