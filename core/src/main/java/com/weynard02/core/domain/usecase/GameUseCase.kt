package com.weynard02.core.domain.usecase

import com.weynard02.core.data.Resource
import com.weynard02.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getAllGame(): Flow<Resource<List<Game>>>
    fun getGame(query: String): Flow<Resource<List<Game>>>
    fun getFavoriteGame(): Flow<List<Game>>
    fun setFavoriteGame(game: Game, state: Boolean)
}