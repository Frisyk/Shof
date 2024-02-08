package com.dicoding.shof.core.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.shof.core.data.local.entity.GamesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {

    @Query("SELECT * FROM games")
    fun getFavoriteGames(): Flow<List<GamesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteGame(games: GamesEntity)

    @Delete
    suspend fun delete(games: GamesEntity)

    @Query("SELECT EXISTS(SELECT * from games where id = :id)")
    fun isFavoriteGame(id: Int): Flow<Boolean>
}