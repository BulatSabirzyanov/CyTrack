package com.example.cytrack.data.remote.service


import com.example.cytrack.data.remote.response.GameTournamentsResponse
import com.example.cytrack.data.remote.response.PlayersResponse
import com.example.cytrack.data.remote.response.Team
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PandaScoreApiService {
    @GET("{game}/tournaments")
    suspend fun getGameTournaments(
        @Path("game") game: String,
        @Query("sort") sort: String = "",
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: String = "50"
    ): List<GameTournamentsResponse>

    @GET("{game}/players")
    suspend fun getPlayerData(
        @Path("game") game: String,
        @Query("sort") sort: String = "",
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: String = "50"
    ): List<PlayersResponse>

    @GET("{game}/teams")
    suspend fun getTeamsData(
        @Path("game") game: String,
        @Query("sort") sort: String = "",
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: String = "50"
    ): List<Team>


    @GET("{game}/players/")
    suspend fun getPlayerInfo(
        @Path("game") game: String?,
        @Query("search[name]") name: String?,
        @Query("sort") sort: String = "",
        @Query("page") page: String = "1",
        @Query("per_page") perPage: String = "50",

    ): List<PlayersResponse>


    @GET("{game}/teams/")
    suspend fun getTeamInfo(
        @Path("game") game: String?,
        @Query("search[name]") name: String?,
        @Query("sort") sort: String = "",
        @Query("page") page: String = "1",
        @Query("per_page") perPage: String = "50",
    ): List<Team>

}
