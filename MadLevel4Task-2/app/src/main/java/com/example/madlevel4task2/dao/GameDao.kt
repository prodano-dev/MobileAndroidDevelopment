package com.example.madlevel4task2.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.madlevel4task2.model.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM gameTable")
    fun getAllGames(): List<Game>

    @Insert
    fun addGameToHistroy(games: Game)

    @Delete
    fun deleteGame(games: Game)

    @Query("DELETE FROM gameTable")
    fun deleteAllGames()

    @Query("SELECT COUNT(*) FROM gameTable WHERE result = 'lose'" )
    fun getLostGames(): Int

    @Query("SELECT COUNT(*) FROM gameTable WHERE result = 'draw'" )
    fun getDrawGames(): Int

    @Query("SELECT COUNT(*) FROM gameTable WHERE result = 'win'" )
    fun getWinGames(): Int


}