package com.example.cytrack.presentation.schedulefragment.tournament

import com.example.cytrack.presentation.schedulefragment.DelegateItem

class TournamentDelegateItem(
    val id: Long,
    private val value: TournamentModel
) : DelegateItem {
    override fun content(): Any = value

    override fun id(): Long = id

    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as TournamentDelegateItem).value == content()
    }
}