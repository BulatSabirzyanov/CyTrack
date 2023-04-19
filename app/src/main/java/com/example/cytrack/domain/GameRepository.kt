package com.example.cytrack.domain

import com.example.cytrack.data.remote.response.GameTournamentsResponse


interface GameRepository {
    suspend fun getGameTournaments(game: String): List<GameTournamentsResponse>


}