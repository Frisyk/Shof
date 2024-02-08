package com.dicoding.shof.core.data

import com.dicoding.shof.core.data.local.LocalDataSource
import com.dicoding.shof.core.data.remote.RemoteDataSource
import com.dicoding.shof.core.data.remote.network.ApiResponse
import com.dicoding.shof.core.domain.model.GameDetails
import com.dicoding.shof.core.domain.model.Games
import com.dicoding.shof.core.domain.repository.IGamesRepository
import com.dicoding.shof.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class GamesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IGamesRepository {

    override suspend fun getAllGames(query: String?): Flow<ApiResponse<List<Games>>> =
        remoteDataSource.getAllGames(query)

    override suspend fun getDetailsGame(id: Int): Flow<ApiResponse<GameDetails>> =
        remoteDataSource.getDetailsGame(id)

    override fun getFavoriteGames(): Flow<List<Games>> {
        return localDataSource.getFavoriteGames().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun setFavoriteGames(games: Games) {
        val gamesEntity = DataMapper.mapDomainToEntity(games)
        withContext(Dispatchers.IO){localDataSource.setFavoriteGames(gamesEntity)}
    }

    override suspend fun deleteFavoriteGame(games: Games) {
        val gamesEntity = DataMapper.mapDomainToEntity(games)
        withContext(Dispatchers.IO){localDataSource.deleteFromFavorites(gamesEntity)}
    }

    override fun isFavGame(id: Int): Flow<Boolean> = localDataSource.isFavoriteGame(id)

}
