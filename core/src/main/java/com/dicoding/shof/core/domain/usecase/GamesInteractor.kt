package com.dicoding.shof.core.domain.usecase

import com.dicoding.shof.core.domain.model.Games
import com.dicoding.shof.core.domain.repository.IGamesRepository


class GamesInteractor(private val gamesRepository: IGamesRepository): GamesUseCase {

    override suspend fun getAllGames(query: String?) = gamesRepository.getAllGames(query)

    override suspend fun getDetailsGame(id: Int) = gamesRepository.getDetailsGame(id)

    override fun getFavoriteGames() = gamesRepository.getFavoriteGames()

    override suspend fun setFavoriteGames(games: Games) = gamesRepository.setFavoriteGames(games)

    override suspend fun deleteFavoriteGame(games: Games) = gamesRepository.deleteFavoriteGame(games)

    override fun checkFavStatus(id: Int) = gamesRepository.isFavGame(id)
}