package com.example.madlevel4task2.database

import android.content.Context
import androidx.room.*
import com.example.madlevel4task2.model.Game
import com.example.madlevel4task2.dao.GameDao

@Database(entities = [Game::class], version = 1, exportSchema = false)

abstract class GamesRoomDatabase: RoomDatabase() {

    abstract fun gamesDao(): GameDao

    companion object {
        private const val DATABASE_NAME = "GAMES_HISTORY"

        @Volatile
        private var gamesRoomDatabaseInstance: GamesRoomDatabase? = null

        fun getDatabase(context: Context) : GamesRoomDatabase? {
            if (gamesRoomDatabaseInstance == null ) {
                synchronized(GamesRoomDatabase::class.java) {
                    if (gamesRoomDatabaseInstance ==null ) {
                        gamesRoomDatabaseInstance = Room.databaseBuilder(context.applicationContext, GamesRoomDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }

            return gamesRoomDatabaseInstance
        }
    }
}