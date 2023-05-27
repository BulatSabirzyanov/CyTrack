package com.example.cytrack.data.remote.response

import com.google.gson.annotations.SerializedName

data class PlayersResponse(
    @SerializedName("first_name")
    var firstName: String?,
    var id: Int?,
    @SerializedName("image_url")
    var imageUrl: String?,
    @SerializedName("last_name")
    var lastName: String?,
    var name: String?,
    @SerializedName("current_team")
    var currentTeam: CurrentTeam?,
    @SerializedName("current_team")
    var currentVideoGame: VideoGame,
    var nationality: String?,
    var role: String?

)
