package com.example.cytrack.data.repository

import com.example.cytrack.data.remote.response.GameTournamentsResponse
import com.example.cytrack.data.remote.service.PandaScoreApiService
import com.example.cytrack.domain.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(private val remoteSource: PandaScoreApiService?) :
    GameRepository {

    override suspend fun getGameTournaments(game: String): List<GameTournamentsResponse> {
        return withContext(Dispatchers.IO) {
            remoteSource!!.getGameTournaments("csgo")
        }

    }
}