package com.example.cytrack.presentation.screens.searchfragment

import com.example.cytrack.data.remote.response.Videogame
import com.google.gson.annotations.SerializedName

data class PlayerModel (
    var age: Int?,
    var birthday: String?,
    var currentTeam: String?,
    var currentVideoGame: Videogame,
    var firstName: String?,
    var imageUrl: String?,
    var lastName: String?,
    var name: String,
    var nationality: String,
    var role: String?,
)
