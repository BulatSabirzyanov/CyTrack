package com.example.cytrack.data.remote.response

import com.google.gson.annotations.SerializedName

data class GameTournamentsResponse(
    @SerializedName("begin_at")
    val begin_at: String,
    @SerializedName("has_bracket")
    val has_bracket: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("matches")
    val matches: List<Match>,
    @SerializedName("name")
    val name: String,
    @SerializedName("league")
    val league: League,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("teams")
    val teams: List<Team>,
    @SerializedName("tier")
    val tier: String,
    @SerializedName("videogame")
    val videogame: Videogame,

    )

data class League(
    @SerializedName("name")
    val name: String
)

data class Match(
    @SerializedName("begin_at")
    val begin_at: String,
    @SerializedName("detailed_stats")
    val detailed_stats: Boolean,
    @SerializedName("draw")
    val draw: Boolean,
    @SerializedName("end_at")
    val end_at: String,
    @SerializedName("forfeit")
    val forfeit: Boolean,
    @SerializedName("game_advantage")
    val game_advantage: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("live")
    val live: Live,
    @SerializedName("match_type")
    val match_type: String,
    @SerializedName("modified_at")
    val modified_at: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("number_of_games")
    val number_of_games: Int,
    @SerializedName("original_scheduled_at")
    val original_scheduled_at: String,
    @SerializedName("rescheduled")
    val rescheduled: Boolean,
    @SerializedName("scheduled_at")
    val scheduled_at: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("tournament_id")
    val tournament_id: Int,
    @SerializedName("winner_id")
    val winner_id: Int?,
    @SerializedName("winner_type")
    val winner_type: String?
)

data class Live(
    @SerializedName("opens_at")
    val opens_at: String?,
    @SerializedName("supported")
    val supported: Boolean,
    @SerializedName("url")
    val url: String?
)


data class Team(
    @SerializedName("acronym")
    val acronym: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val image_url: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("modified_at")
    val modified_at: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
)

data class Videogame(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
)
