package com.example.madlevel5task2.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel5task2.Dao.GameDao
import com.example.madlevel5task2.Database.GameRoomDatabase
import com.example.madlevel5task2.Model.Game

class GameRepository(context: Context) {

    private var gameDao: GameDao

    init {
        val gameRoomDatabase = GameRoomDatabase.getDatabase(context)
        gameDao = gameRoomDatabase!!.gameDao()
    }

    fun getAllGames(): LiveData<List<Game>> {
        return gameDao?.getAllGames() ?: MutableLiveData(emptyList())
    }

    suspend fun addGame(game: Game){
        return gameDao.addGame(game)
    }

    suspend fun deleteGame(game: Game) {
        return gameDao.deleteGame(game)
    }
    suspend fun deleteAllGames() {
        return gameDao.deleteAllGames()
    }
}