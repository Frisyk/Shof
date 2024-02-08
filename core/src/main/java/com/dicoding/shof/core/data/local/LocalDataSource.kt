package com.dicoding.shof.core.data.local


import com.dicoding.shof.core.data.local.entity.GamesEntity
import com.dicoding.shof.core.data.local.room.GamesDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gamesDao: GamesDao) {


    fun getFavoriteGames(): Flow<List<GamesEntity>> = gamesDao.getFavoriteGames()

    suspend fun setFavoriteGames(games: GamesEntity) = gamesDao.insertFavoriteGame(games)

    suspend fun deleteFromFavorites(games: GamesEntity) = gamesDao.delete(games)

    fun isFavoriteGame(id: Int): Flow<Boolean> = gamesDao.isFavoriteGame(id)

}