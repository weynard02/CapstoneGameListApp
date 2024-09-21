package com.weynard02.capstonegamelistapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.weynard02.core.data.Resource
import com.weynard02.core.domain.model.Game
import com.weynard02.core.domain.usecase.GameUseCase

class HomeViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    val games = gameUseCase.getAllGame().asLiveData()

    fun searchGame(query: String) : LiveData<Resource<List<Game>>> {
        return gameUseCase.getGame(query).asLiveData()
    }
}