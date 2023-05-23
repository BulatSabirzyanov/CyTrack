package com.example.cytrack.data.remote.response

import com.google.gson.annotations.SerializedName

data class PlayersResponse(
    var age: Int?,
    var birthday: String?,
    @SerializedName("current_team")
    var currentTeam: String?,
    @SerializedName("current_videogame")
    var current_videogame: Videogame,
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
