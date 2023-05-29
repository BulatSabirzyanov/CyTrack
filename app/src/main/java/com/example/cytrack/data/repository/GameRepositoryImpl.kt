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

    override suspend fun getGameTournaments(game: String, page: Int): List<GameTournamentsResponse> {
        return withContext(ioDispatcher) {
            remoteSource.getGameTournaments(game, page = page)
        }

    }

    override suspend fun getPlayerData(game: String, page: Int): List<PlayersResponse> {
        return withContext(ioDispatcher){
            remoteSource.getPlayerData(game, page = page)
        }
    }

    override suspend fun getTeamsData(game: String, page: Int): List<Team> {
        return withContext(ioDispatcher){
            remoteSource.getTeamsData(game, page = page)
        }
    }

    override suspend fun getPlayerInfo(game: String,name :String?):List<PlayersResponse>{
        return withContext(ioDispatcher){
            remoteSource.getPlayerInfo(game = game, name = name)
        }
    }


}
