package com.humanverse.driso_imagegallery.data.image
import com.google.gson.annotations.SerializedName

class ImageModelItem(
    @SerializedName("alt_description")
    val alt_description: String,
    @SerializedName("blur_hash")
    val blur_hash: String,
    @SerializedName("categories")
    val categories: List<Any>,
    @SerializedName("color")
    val color: String,
    @SerializedName("created_at")
    val created_at: String,
    @SerializedName("current_user_collections")
    val current_user_collections: List<Any>,
    @SerializedName("description")
    val description: Any,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("liked_by_user")
    val liked_by_user: Boolean,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("links")
    val links: Links,
    @SerializedName("promoted_at")
    val promoted_at: Any,
    @SerializedName("sponsorship")
    val sponsorship: Sponsorship,
    @SerializedName("topic_submissions")
    val topic_submissions: TopicSubmissions,
    @SerializedName("updated_at")
    val updated_at: String,
    @SerializedName("urls")
    val urls: Urls,
    @SerializedName("user")
    val user: User,
    @SerializedName("width")
    val width: Int
)