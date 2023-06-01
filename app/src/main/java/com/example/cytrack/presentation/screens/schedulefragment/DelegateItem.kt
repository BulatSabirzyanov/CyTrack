package com.example.cytrack.presentation.screens.schedulefragment

interface DelegateItem {
    fun content(): Any

    fun id(): Long

    fun compareToOther(other: DelegateItem): Boolean
}
