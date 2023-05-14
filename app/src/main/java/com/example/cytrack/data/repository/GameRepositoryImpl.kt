package com.example.cytrack.data.repository

import com.example.cytrack.data.remote.response.GameTournamentsResponse
import com.example.cytrack.data.remote.service.PandaScoreApiService
import com.example.cytrack.domain.GameRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val remoteSource: PandaScoreApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) :
    GameRepository {

    override suspend fun getGameTournaments(game: String): List<GameTournamentsResponse> {
        return withContext(ioDispatcher) {
            remoteSource.getGameTournaments(game)
        }

    }
}
