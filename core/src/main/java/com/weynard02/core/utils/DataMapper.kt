package com.weynard02.core.utils

import com.weynard02.core.data.source.local.entity.GameEntity
import com.weynard02.core.data.source.remote.response.GameResponse
import com.weynard02.core.domain.model.Game

object DataMapper {
    fun mapResponsesToEntities(input: List<GameResponse>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                id = it.id,
                name = it.name,
                released = it.released,
                backgroundImage = it.backgroundImage,
                rating = it.rating,
                playtime = it.playtime,
                isFavorite = false
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                id = it.id,
                name = it.name,
                released = it.released ?: "",
                backgroundImage = it.backgroundImage ?: "",
                rating = it.rating ?: 0.0,
                playtime = it.playtime ?: 0,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Game) = GameEntity(
        id = input.id,
        name = input.name,
        released = input.released,
        backgroundImage = input.backgroundImage,
        rating = input.rating,
        playtime = input.playtime,
        isFavorite = input.isFavorite
    )
}