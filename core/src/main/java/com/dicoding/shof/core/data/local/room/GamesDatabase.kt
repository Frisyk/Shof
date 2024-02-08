package com.dicoding.shof.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.shof.core.data.local.entity.GamesEntity

@Database(entities = [GamesEntity::class], version = 1, exportSchema = false)
abstract class GamesDatabase : RoomDatabase() {

    abstract fun gamesDao(): GamesDao

}