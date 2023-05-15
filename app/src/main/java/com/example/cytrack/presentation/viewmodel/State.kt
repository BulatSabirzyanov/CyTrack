package com.example.cytrack.presentation.viewmodel

import com.example.cytrack.presentation.schedulefragment.date.DateModel
import com.example.cytrack.presentation.schedulefragment.game.GameModel
import com.example.cytrack.presentation.schedulefragment.tournament.TournamentModel

data class State(
    val dateList: List<DateModel>,
    val tournamentList: List<TournamentModel>,
    val gameList: List<GameModel>,
    val loadingState: Boolean
)
