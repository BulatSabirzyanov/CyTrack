package com.example.cytrack.data.repository

import com.example.cytrack.data.remote.response.GameTournamentsResponse
import com.example.cytrack.data.remote.response.PlayersResponse
import com.example.cytrack.data.remote.response.Team
import com.example.cytrack.data.remote.service.PandaScoreApiService
import com.example.cytrack.domain.GameRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class GameRepositoryImpl (
    private val remoteSource: PandaScoreApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) :
    GameRepository {

    override suspend fun getGameTournaments(game: String): List<GameTournamentsResponse> {
        return withContext(ioDispatcher) {
            remoteSource.getGameTournaments(game)
        }

    }

    override suspend fun getPlayerData(game: String): List<PlayersResponse> {
        return withContext(ioDispatcher){
            remoteSource.getPlayerData(game)
        }
    }

    override suspend fun getTeamsData(game: String): List<Team> {
        return withContext(ioDispatcher){
            remoteSource.getTeamsData(game)
        }
    }


}
