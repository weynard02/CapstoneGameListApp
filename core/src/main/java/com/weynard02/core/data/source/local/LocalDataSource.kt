package com.weynard02.core.data.source.local

import com.weynard02.core.data.source.local.entity.GameEntity
import com.weynard02.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gameDao: GameDao) {

    fun getAllGame(): Flow<List<GameEntity>> = gameDao.getAllGame()

    fun getGame(query: String): Flow<List<GameEntity>> = gameDao.getGame(query)

    fun getFavoriteGame(): Flow<List<GameEntity>> = gameDao.getFavoriteGame()

    suspend fun insertGame(gameList: List<GameEntity>) = gameDao.insertGame(gameList)

    fun setFavoriteGame(game: GameEntity, newState: Boolean) {
        game.isFavorite = newState
        gameDao.updateFavoriteGame(game)
    }
}