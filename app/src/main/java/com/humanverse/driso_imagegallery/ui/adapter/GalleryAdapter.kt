package com.humanverse.driso_imagegallery.ui.adapter

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.humanverse.driso_imagegallery.data.image.ImageModelItem
import com.humanverse.driso_imagegallery.databinding.ItemGalleryBinding
import com.humanverse.driso_imagegallery.ui.view.PhotoViewActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GalleryAdapter @Inject constructor(
    @ApplicationContext val context: Context
) :
    RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {
    class GalleryViewHolder(
        private val binding: ItemGalleryBinding,
        private val context: Context,
        private val adapter: GalleryAdapter
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(position: Int) {
            val model = adapter.getCurrentDataSet()[position]

            /**
             * Image caching is done with diskcache strategy
             */
            Glide
                .with(context)
                .load(model.urls.small)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imageView)

            binding.imageView.setOnClickListener {
                val intent = Intent(context, PhotoViewActivity::class.java)
                intent.putExtra("URL", model.urls.regular)
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }

    }

    private val diffUtilsCallBack =
        object : DiffUtil.ItemCallback<ImageModelItem>() {
            override fun areItemsTheSame(
                oldItem: ImageModelItem,
                newItem: ImageModelItem,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ImageModelItem,
                newItem: ImageModelItem,
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }


    fun clearDataSet() {
        differ.submitList(null)
        this.notifyDataSetChanged()
    }

    fun getCurrentDataSet(): MutableList<ImageModelItem> =
        ArrayList(differ.currentList.toMutableList()).toMutableList()

    private var differ: AsyncListDiffer<ImageModelItem> =
        AsyncListDiffer(this, diffUtilsCallBack)

    fun submitListData(dataList: MutableList<ImageModelItem>) {
        differ.submitList(dataList)
    }

    override fun getItemCount(): Int = differ.currentList.size
    fun addDataSet(dataList: MutableList<ImageModelItem>) {
        val previousList =
            ArrayList<ImageModelItem>(differ.currentList.toMutableList().toMutableSet().toMutableList())
        previousList.addAll(dataList)
        differ.submitList(previousList)
    }
    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder =
        GalleryViewHolder(
            ItemGalleryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            context, this
        )
}