package com.example.cytrack.domain

import com.example.cytrack.data.remote.response.GameTournamentsResponse
import com.example.cytrack.data.remote.response.PlayersResponse
import com.example.cytrack.data.remote.response.Team


interface GameRepository {
    suspend fun getGameTournaments(game: String, page: Int): List<GameTournamentsResponse>

    suspend fun getPlayerData(game: String,page : Int): List<PlayersResponse>

    suspend fun getTeamsData(game: String, page: Int): List<Team>

    suspend fun getPlayerInfo(gema: String, name: String?): List<PlayersResponse>
}
