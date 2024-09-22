package com.weynard02.capstonegamelistapp.detail

import androidx.lifecycle.ViewModel
import com.weynard02.core.domain.model.Game
import com.weynard02.core.domain.usecase.GameUseCase

class DetailViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    fun setFavoriteGame(game: Game, newStatus: Boolean) {
        gameUseCase.setFavoriteGame(game, newStatus)
    }
}
