package com.example.madlevel5task2.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.madlevel5task2.Model.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM gameTable")
    fun getAllGames(): LiveData<List<Game>>

    @Insert
    suspend fun addGame(game:Game)

    @Delete
    suspend fun deleteGame(game: Game)

   @Query("DELETE FROM gameTable")
    suspend fun deleteAllGames()
}