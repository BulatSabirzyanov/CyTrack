package com.example.cytrack.data.remote.response

import com.google.gson.annotations.SerializedName

data class GameTournamentsResponse(
    @SerializedName("begin_at")
    val beginAt: String,
    @SerializedName("has_bracket")
    val hasBracket: Boolean,
    val id: Int,
    val matches: List<Match>,
    val name: String,
    val league: League,
    val slug: String,
    val teams: List<Team>,
    val tier: String,
    val videogame: VideoGame,
    )
