package com.example.cytrack.data.remote.service


import com.example.cytrack.data.remote.response.GameTournamentsResponse
import com.example.cytrack.data.remote.response.PlayersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PandaScoreApiService {
    @GET("{game}/tournaments")
    suspend fun getGameTournaments(
        @Path("game") game: String,
        @Query("sort") sort: String = "",
        @Query("page") page: String = "1",
        @Query("per_page") perPage: String = "50"
    ): List<GameTournamentsResponse>

    @GET("{game}/tournaments")
    suspend fun getPlayerData(
        @Path("game") game: String,
        @Query("sort") sort: String = "",
        @Query("page") page: String = "1",
        @Query("per_page") perPage: String = "50"
    ): List<PlayersResponse>


}
