package com.example.cytrack.presentation.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cytrack.data.remote.response.GameTournamentsResponse
import com.example.cytrack.domain.GameRepository
import com.example.cytrack.presentation.screens.schedulefragment.game.GameModel
import com.example.cytrack.presentation.screens.schedulefragment.tournament.TournamentModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ScheduleFragmentViewModel @AssistedInject constructor(
    private val gameRepository: GameRepository,
    @Assisted(ASSISTED_GAME_NAME) private var game: String
) : ViewModel() {

    var isLoading = false

    init {
        getSchedule(game)
    }


    private val _listOfGames: MutableLiveData<List<GameModel>> = MutableLiveData(emptyList())
    val listOfGames: LiveData<List<GameModel>> = _listOfGames

    private val _listOfTournaments: MutableLiveData<List<TournamentModel>> =
        MutableLiveData(emptyList())
    val listOfTournaments: LiveData<List<TournamentModel>> = _listOfTournaments

    private val _progressBarState: MutableLiveData<Boolean> = MutableLiveData(true)
    val progressBarState: LiveData<Boolean> = _progressBarState

    private var currentPage = 1
    fun getSchedule(game: String) {
        if (!isLoading) {
            isLoading = true

            viewModelScope.launch(Dispatchers.IO) {
                runCatching {
                    gameRepository.getGameTournaments(game, page = currentPage)
                }.onSuccess { value: List<GameTournamentsResponse> ->

                    val filteredTournaments = value.filter { !it.hasBracket }

                    val tournamentModels = filteredTournaments.map { tournament ->

                        TournamentModel(
                            id = tournament.id.toLong(),
                            date = tournament.beginAt,
                            name = tournament.league.name
                        )
                    }

                    val gameModels = filteredTournaments.flatMap { tournament ->
                        tournament.matches.mapNotNull { match ->


                            if (match.status != "finished" && match.status != "canceled") {

                                val teams = match.name.split(" ")

                                val team1 =
                                    tournament.teams.find { it.name == teams.getOrNull(teams.lastIndex - 2) }
                                val team2 = tournament.teams.find { it.name == teams.last() }
                                val dateTime = LocalDateTime.parse(match.beginAt, DateTimeFormatter.ISO_DATE_TIME)
                                val timeString = dateTime.format(DateTimeFormatter.ofPattern("HH:mm\n"))
                                val dateString = dateTime.format(DateTimeFormatter.ofPattern("dd-MM\n"))

                                if (team1 != null && team2 != null) {
                                    GameModel(
                                        id = match.tournamentId.toLong(),
                                        date = "$timeString$dateString",
                                        team1 = team1.name,
                                        team2 = team2.name,
                                        team1Icon = team1.imageUrl,
                                        team2Icon = team2.imageUrl,
                                    )
                                } else {
                                    null
                                }
                            } else {
                                null
                            }
                        }
                    }


                    val filteredTournamentModels = tournamentModels.filter { tournament ->
                        gameModels.any { game ->
                            game.id == tournament.id
                        }
                    }

                    _listOfTournaments.postValue((_listOfTournaments.value?: emptyList()) + filteredTournamentModels)
                    _listOfGames.postValue((_listOfGames.value?: emptyList()) + gameModels)
                    _progressBarState.postValue(false)
                    currentPage++
                    isLoading = false
                }
            }
        }
    }


    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted(ASSISTED_GAME_NAME) game: String
        ): ScheduleFragmentViewModel
    }

    companion object {
        const val ASSISTED_GAME_NAME = "GAME"
        const val ASSISTED_NAME = "NAME"
    }
}
