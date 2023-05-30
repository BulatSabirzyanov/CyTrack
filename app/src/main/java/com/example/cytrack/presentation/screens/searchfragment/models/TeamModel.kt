package com.example.cytrack.presentation.screens.searchfragment.models

import com.example.cytrack.presentation.screens.searchfragment.models.PlayerModel

data class TeamModel(
    val acronym: String?,
    val id: Int,
    val imageUrl: String?,
    val location: String?,
    val name: String,
    val players: List<PlayerModel>?,
    val slug: String
)
