package com.weynard02.core.domain.usecase

import com.weynard02.core.data.Resource
import com.weynard02.core.domain.model.Game
import com.weynard02.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository) : GameUseCase {
    override fun getAllGame(): Flow<Resource<List<Game>>> = gameRepository.getAllGame()

    override fun getGame(query: String): Flow<Resource<List<Game>>> = gameRepository.getGame(query)

    override fun getFavoriteGame(): Flow<List<Game>> = gameRepository.getFavoriteGame()

    override fun setFavoriteGame(game: Game, state: Boolean) = gameRepository.setFavoriteGame(game, state)

}