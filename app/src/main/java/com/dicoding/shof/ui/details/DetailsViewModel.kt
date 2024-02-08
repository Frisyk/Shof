package com.dicoding.shof.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.shof.core.data.remote.network.ApiResponse
import com.dicoding.shof.core.domain.model.GameDetails
import com.dicoding.shof.core.domain.model.Games
import com.dicoding.shof.core.domain.usecase.GamesUseCase
import kotlinx.coroutines.launch

class DetailsViewModel(private val gamesUseCase: GamesUseCase) : ViewModel() {
    fun setFavoriteGame(games: Games){
        viewModelScope.launch {
            gamesUseCase.setFavoriteGames(games)
        }
    }

    fun deleteFavGame(games: Games) {
        viewModelScope.launch {
            gamesUseCase.deleteFavoriteGame(games)
        }
    }

    fun isFavGame(id: Int) =
        gamesUseCase.checkFavStatus(id).asLiveData()

    suspend fun getDetailsGame(id: Int): LiveData<ApiResponse<GameDetails>> {
        return gamesUseCase.getDetailsGame(id).asLiveData(viewModelScope.coroutineContext)
    }

}