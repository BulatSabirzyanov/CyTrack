package com.example.cytrack.data.remote.response

import com.google.gson.annotations.SerializedName

data class PlayersResponse(
    val id: Int,
    @SerializedName("current_team")
    var currentTeam: CurrentTeam?,
    @SerializedName("current_videogame")
    var current_videogame: VideoGame,
    @SerializedName("first_name")
    var firstName: String?,
    @SerializedName("image_url")
    var imageUrl: String?,
    @SerializedName("last_name")
    var lastName: String?,
    var name: String,
    var nationality: String,
    var role: String?,

)
