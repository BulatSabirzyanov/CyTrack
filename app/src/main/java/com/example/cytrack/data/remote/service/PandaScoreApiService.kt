package com.example.cytrack.data.remote.service


import com.example.cytrack.data.remote.response.GameTournamentsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PandaScoreApiService {
    @GET("{game}/tournaments")
    suspend fun getGameTournaments(
        @Path("game") game: String,
        @Query("search[tier]") tier: String = "s",
        @Query("sort") sort: String = "",
        @Query("page") page: String = "1",
        @Query("per_page") per_page: String = "50"
    ): List<GameTournamentsResponse>
}