package com.example.cytrack.presentation.screens.searchfragment

import java.text.SimpleDateFormat
import java.util.Date

data class TeamModel(
    val acronym: String?,
    val id: Int,
    val imageUrl: String?,
    val location: String?,
    val name: String,
    val players: List<PlayerModel>?,
    val slug: String
)
