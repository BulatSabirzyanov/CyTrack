package com.example.cytrack.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cytrack.R
import com.example.cytrack.data.remote.response.PlayersResponse
import com.example.cytrack.data.remote.response.Team
import com.example.cytrack.domain.GameRepository
import com.example.cytrack.presentation.screens.searchfragment.models.HeadingModel
import com.example.cytrack.presentation.screens.searchfragment.models.PlayerModel
import com.example.cytrack.presentation.screens.searchfragment.models.TeamModel
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
    var currentGame = game
    var isPlayerLoading = false
    var isTeamLoading = false
    var currentPlayersPage = 1
    var currentTeamsPage = 1
    init {
        getPlayersData(currentGame)
        getTeamsData(currentGame)
    }

    private var _listOfSearchForm: MutableLiveData<List<Any>> =
        MutableLiveData(emptyList())
    var listOfPlayersSearchForm: LiveData<List<Any>> = _listOfSearchForm


    private var _listOfPlayers: MutableLiveData<List<PlayerModel>> = MutableLiveData(emptyList())
    var listOfPlayers: LiveData<List<PlayerModel>> = _listOfPlayers

    private var _listOfTeams: MutableLiveData<List<TeamModel>> =
        MutableLiveData(emptyList())
    var listOfTeams: LiveData<List<TeamModel>> = _listOfTeams


    fun clearLists(){
        _listOfPlayers.value = emptyList()
        _listOfTeams.value = emptyList()
    }

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

    fun getInfoSearchForm(game: String?, name: String?) {
        var listSearchForm = mutableListOf<Any>()
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
                if (playerModels.isNotEmpty()){
                    listSearchForm.add(HeadingModel(
                        text = R.string.player_heading_item_text
                    ))
                    listSearchForm.addAll(playerModels)
                }



            }
            runCatching {
                gameRepository.getTeamInfo(game,name)
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
                if (teamModels.isNotEmpty()){
                    listSearchForm.add(HeadingModel(
                        text = R.string.team_heading_item_text
                    ))
                    listSearchForm.addAll(teamModels)
                }

                _listOfSearchForm.postValue(listSearchForm)

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


