package com.dicoding.shof.favorites.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.shof.core.domain.usecase.GamesUseCase

class FavoritesViewModel(gamesUseCase: GamesUseCase) : ViewModel() {
    val favoritesGames = gamesUseCase.getFavoriteGames().asLiveData()
}