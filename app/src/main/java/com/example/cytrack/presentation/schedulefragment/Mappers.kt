package com.example.cytrack.presentation.schedulefragment

import com.example.cytrack.presentation.schedulefragment.game.GameDelegateItem
import com.example.cytrack.presentation.schedulefragment.game.GameModel
import com.example.cytrack.presentation.schedulefragment.tournament.TournamentDelegateItem
import com.example.cytrack.presentation.schedulefragment.tournament.TournamentModel

fun List<GameModel>.concatenateWithTournament(tournaments: List<TournamentModel>): List<DelegateItem> {
    val delegateItemList: MutableList<DelegateItem> = mutableListOf()

    tournaments.forEach { tournamentModel ->
        delegateItemList.add(
            TournamentDelegateItem(id = tournamentModel.id, value = tournamentModel)
        )
        val id = tournamentModel.id
        val allGames = this.filter { game ->
            game.id == id
        }
        allGames.forEach { model ->
            delegateItemList.add(
                GameDelegateItem(
                    id = model.id,
                    value = model,
                )
            )
        }
    }
    return delegateItemList

}