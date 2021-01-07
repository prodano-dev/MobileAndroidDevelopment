package com.example.madlevel4task2.repositroy

import android.content.Context
import com.example.madlevel4task2.model.Game
import com.example.madlevel4task2.dao.GameDao
import com.example.madlevel4task2.database.GamesRoomDatabase

class GamesRepository(context: Context) {

    private var gameDao: GameDao

    init {
        val database = GamesRoomDatabase.getDatabase(context)
        gameDao = database!!.gamesDao()
    }

    suspend fun getAllGames(): List<Game> {
        return gameDao.getAllGames()
    }

    suspend fun addGameToHistroy(game: Game) {
        gameDao.addGameToHistroy(game)
    }

    suspend fun deleteGame(game: Game) {
        gameDao.deleteGame(game)
    }

    suspend fun deleteAllGames() {
        gameDao.deleteAllGames()
    }

    suspend fun getLostGames(): Int {
        return gameDao.getLostGames()
    }

    suspend fun getDrawGames(): Int {
        return gameDao.getDrawGames()
    }

    suspend fun getWinGames(): Int {
        return gameDao.getWinGames()
    }
}