package com.weynard02.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.weynard02.core.domain.usecase.GameUseCase

class FavoriteViewModel(gameUseCase: GameUseCase) : ViewModel() {
    val favoriteGame = gameUseCase.getFavoriteGame().asLiveData()
}