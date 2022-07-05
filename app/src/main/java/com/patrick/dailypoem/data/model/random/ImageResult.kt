package com.patrick.dailypoem.data.model.random

import com.google.gson.annotations.SerializedName

data class ImageResult(
    @SerializedName("id")
    val id: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("promoted_at")
    val promotedAt: Any,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("color")
    val color: String,
    @SerializedName("blur_hash")
    val blurHash: String,
    @SerializedName("description")
    val description: Any,
    @SerializedName("alt_description")
    val altDescription: String,
    @SerializedName("urls")
    val urls: Urls,
    @SerializedName("links")
    val links: Links,
    @SerializedName("categories")
    val categories: List<Any>,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean,
    @SerializedName("current_user_collections")
    val currentUserCollections: List<Any>,
    @SerializedName("user")
    val user: User,
    @SerializedName("views")
    val views: Int,
    @SerializedName("downloads")
    val downloads: Int
)
