package com.example.cytrack.data.remote.response

import com.google.gson.annotations.SerializedName

data class CurrentTeam(
    val id:Int,
    @SerializedName("first_name")
    val imageUrl: String?,
    val location: String?,
    val name:  String?,
)
