package com.example.cytrack.data.remote.response

import com.google.gson.annotations.SerializedName

data class Match(
    @SerializedName("begin_at")
    val beginAt: String,
    @SerializedName("detailed_stats")
    val detailedStats: Boolean,
    val draw: Boolean,
    @SerializedName("end_at")
    val endAt: String,
    val forfeit: Boolean,
    @SerializedName("game_advantage")
    val gameAdvantage: String?,
    val id: Int,
    val live: Live,
    @SerializedName("match_type")
    val matchType: String,
    @SerializedName("modified_at")
    val modifiedAt: String,
    val name: String,
    @SerializedName("number_of_games")
    val numberOfGames: Int,
    @SerializedName("original_scheduled_at")
    val originalScheduledAt: String,
    val rescheduled: Boolean,
    @SerializedName("scheduled_at")
    val scheduledAt: String,
    val slug: String,
    val status: String,
    @SerializedName("tournament_id")
    val tournamentId: Int,
    @SerializedName("winner_id")
    val winnerId: Int?,
    @SerializedName("winner_type")
    val winnerType: String?
)
