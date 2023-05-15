package com.example.cytrack.presentation.schedulefragment.game

import com.example.cytrack.presentation.schedulefragment.DelegateItem

class GameDelegateItem(
    val id: Long,
    private val value: GameModel
) : DelegateItem {
    override fun content(): Any = value

    override fun id(): Long = id

    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as GameDelegateItem).value == content()
    }

}