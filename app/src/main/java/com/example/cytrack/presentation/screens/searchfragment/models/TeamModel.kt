package com.example.cytrack.presentation.screens.searchfragment.models


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamModel(
    val acronym: String?,
    val id: Int,
    val imageUrl: String?,
    val location: String?,
    val name: String,
    val players: List<TeamPlayerModel>?,
    val slug: String
): Parcelable
