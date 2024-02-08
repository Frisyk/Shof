package com.dicoding.shof.core.domain.repository

import com.dicoding.shof.core.data.remote.network.ApiResponse
import com.dicoding.shof.core.domain.model.GameDetails
import com.dicoding.shof.core.domain.model.Games
import kotlinx.coroutines.flow.Flow

interface IGamesRepository {

    suspend fun getAllGames(query: String? = null): Flow<ApiResponse<List<Games>>>

    suspend fun getDetailsGame(id: Int): Flow<ApiResponse<GameDetails>>

    fun getFavoriteGames(): Flow<List<Games>>

    suspend fun setFavoriteGames(games: Games)

    suspend fun deleteFavoriteGame(games: Games)

    fun isFavGame(id: Int): Flow<Boolean>

}