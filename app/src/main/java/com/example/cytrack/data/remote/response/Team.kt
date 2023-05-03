package com.example.cytrack.data.remote.response

import com.google.gson.annotations.SerializedName

data class Team(
    val acronym: String?,
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    val location: String,
    @SerializedName("modified_at")
    val modifiedAt: String,
    val name: String,
    val slug: String
)