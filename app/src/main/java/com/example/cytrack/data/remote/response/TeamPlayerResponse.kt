package com.example.cytrack.data.remote.response

import com.google.gson.annotations.SerializedName

data class TeamPlayerResponse(
    val age: Int?,
    val birthday: String?,
    @SerializedName("first_name")
    val firstName: String?,
    val id: Int?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("last_name")
    val lastName: String?,
    val name: String?,
    val nationality: String?,
    val role: String?,
    val slug: String?
)

