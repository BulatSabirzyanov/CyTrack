package com.example.cytrack.presentation.screens.searchfragment.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerModel (
    var id: Int?,
    var currentTeam: String?,
    var firstName: String?,
    var imageUrl: String?,
    var lastName: String?,
    var name: String?,
    var nationality: String?,
    var role: String?,
):Parcelable
