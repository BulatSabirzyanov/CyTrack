package com.example.cytrack.presentation.screens.searchfragment.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class TeamPlayerModel(
    val age: Int?,
    val birthday: String?,
    val firstName: String?,
    val id: Int?,
    val imageUrl: String?,
    val lastName: String?,
    val name: String?,
    val nationality: String?,
    val role: String?,
    val slug: String?
): Parcelable
