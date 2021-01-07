package com.example.madlevel5task2.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel5task2.Model.Game
import com.example.madlevel5task2.Repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private var ioScope = CoroutineScope(Dispatchers.IO)
    private var gameRepository = GameRepository(application.applicationContext)

    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()
    var games: LiveData<List<Game>> = gameRepository.getAllGames()

    fun addGame(game: Game) {
        ioScope.launch {
            gameRepository.addGame(game)
        }
        success.value = true
    }

    fun deleteGame(game: Game) {
        ioScope.launch {
            gameRepository.deleteGame(game)
        }
    }

    fun deleteAllGames() {
        ioScope.launch {
            gameRepository.deleteAllGames()
        }
    }

}