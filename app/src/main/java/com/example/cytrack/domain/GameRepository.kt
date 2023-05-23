package com.example.cytrack.domain

import com.example.cytrack.data.remote.response.GameTournamentsResponse
import com.example.cytrack.data.remote.response.PlayersResponse


interface GameRepository {
    suspend fun getGameTournaments(game: String): List<GameTournamentsResponse>

    suspend fun getPlayerData(game: String): List<PlayersResponse>
}
