package com.example.cybertracker.data.mappers

import com.example.cybertracker.domain.entity.TournamentsEntity
import com.example.cytrack.data.remote.response.GameTournamentsResponse

class GameTournamentsResponseMapper {
    fun map(item: GameTournamentsResponse?): TournamentsEntity {
        return (item?.let {
            TournamentsEntity(
                id = item.id,
                name = item.name,
                tier = item.tier,
                videogame = item.videogame.name

            )
        } ?: TournamentsEntity(
            id = 0,
            name = "",
            tier = "",
            videogame = ""
        )
                )
    }
}