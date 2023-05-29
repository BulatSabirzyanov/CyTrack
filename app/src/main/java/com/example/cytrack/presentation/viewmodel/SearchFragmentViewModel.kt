package com.example.cytrack.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cytrack.data.remote.response.GameTournamentsResponse
import com.example.cytrack.data.remote.response.PlayersResponse
import com.example.cytrack.data.remote.response.Team
import com.example.cytrack.domain.GameRepository
import com.example.cytrack.presentation.screens.schedulefragment.tournament.TournamentModel
import com.example.cytrack.presentation.screens.searchfragment.PlayerModel
import com.example.cytrack.presentation.screens.searchfragment.TeamModel
import com.example.cytrack.presentation.viewmodel.ScheduleFragmentViewModel.Companion.ASSISTED_GAME_NAME
import com.example.cytrack.presentation.viewmodel.ScheduleFragmentViewModel.Companion.ASSISTED_NAME
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchFragmentViewModel @AssistedInject constructor(
    private val gameRepository: GameRepository,
    @Assisted(ASSISTED_GAME_NAME) private var game: String,
    @Assisted(ASSISTED_NAME) private var name: String?
) : ViewModel() {
    var isPlayerLoading = false
    var isTeamLoading = false
    private var currentPlayersPage = 1
    private var currentTeamsPage = 1
    init {
        getPlayersData(game)
        getTeamsData(game)
        getPlayerInfo(game, name)
    }

    private var _listOfPlayersSearchForm: MutableLiveData<List<PlayerModel>> =
        MutableLiveData(emptyList())
    var listOfPlayersSearchForm: LiveData<List<PlayerModel>> = _listOfPlayersSearchForm

    private var _listOfPlayers: MutableLiveData<List<PlayerModel>> = MutableLiveData(emptyList())
    var listOfPlayers: LiveData<List<PlayerModel>> = _listOfPlayers

    private var _listOfTeams: MutableLiveData<List<TeamModel>> =
        MutableLiveData(emptyList())
    var listOfTeams: LiveData<List<TeamModel>> = _listOfTeams



    internal fun getPlayersData(game: String) {
        if (!isPlayerLoading) {
            isPlayerLoading = true
            viewModelScope.launch(Dispatchers.IO) {
                runCatching {
                    gameRepository.getPlayerData(game, currentPlayersPage)
                }.onSuccess { value: List<PlayersResponse> ->
                    val playerModels = value.map { player ->
                        PlayerModel(

                            currentTeam = player.currentTeam?.name,
                            firstName = player.firstName,
                            imageUrl = player.imageUrl,
                            lastName = player.lastName,
                            name = player.name,
                            nationality = player.nationality,
                            role = player.role,
                            id = player.id
                        )
                    }
                    _listOfPlayers.postValue((_listOfPlayers.value ?: emptyList()) + playerModels)

                    isPlayerLoading = false
                    currentPlayersPage++
                }
            }

        }
    }


    internal fun getTeamsData(game: String) {
        if (!isTeamLoading) {
            isTeamLoading = true
            viewModelScope.launch(Dispatchers.IO) {
                runCatching {
                    gameRepository.getTeamsData(game, currentTeamsPage)
                }.onSuccess { teams: List<Team> ->
                    val teamModels = teams.map { team ->
                        TeamModel(
                            acronym = team.acronym,
                            id = team.id,
                            imageUrl = team.imageUrl,
                            location = team.location,
                            name = team.name,
                            players = team.players.map { playerResponse ->
                                PlayerModel(
                                    currentTeam = playerResponse.currentTeam?.name,
                                    firstName = playerResponse.firstName,
                                    imageUrl = playerResponse.imageUrl,
                                    lastName = playerResponse.lastName,
                                    name = playerResponse.name,
                                    nationality = playerResponse.nationality,
                                    role = playerResponse.role,
                                    id = playerResponse.id,
                                )

                            },
                            slug = team.slug
                        )
                    }

                    _listOfTeams.postValue((_listOfTeams.value ?: emptyList()) + teamModels)
                    isTeamLoading = false
                    currentTeamsPage++
                }
            }
        }
    }

    private fun getPlayerInfo(game: String, name: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                gameRepository.getPlayerInfo(game, name)
            }.onSuccess { value: List<PlayersResponse> ->
                val playerModels = value.map { player ->
                    PlayerModel(

                        currentTeam = player.currentTeam?.name,
                        firstName = player.firstName,
                        imageUrl = player.imageUrl,
                        lastName = player.lastName,
                        name = player.name,
                        nationality = player.nationality,
                        role = player.role,
                        id = player.id
                    )
                }
                _listOfPlayersSearchForm.postValue(playerModels)

            }
        }

    }


    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted(ASSISTED_GAME_NAME) game: String,
            @Assisted(ASSISTED_NAME) name: String?
        ): SearchFragmentViewModel
    }


}


