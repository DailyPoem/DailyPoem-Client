package com.patrick.dailypoem.data.model.random

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("portfolio_url")
    val portfolioUrl: Any,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("links")
    val links: LinksX,
    @SerializedName("profile_image")
    val profileImage: ProfileImage,
    @SerializedName("total_collections")
    val totalCollections: Int,
    @SerializedName("total_likes")
    val totalLikes: Int,
    @SerializedName("total_photos")
    val totalPhotos: Int
)
