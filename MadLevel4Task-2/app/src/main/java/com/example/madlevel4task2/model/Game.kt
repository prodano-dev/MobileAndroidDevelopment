package com.example.madlevel4task2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "gameTable")
data class Game(

    @ColumnInfo(name = "result")
    var gameResult: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "computerChoice")
    var computerChoice: Int,

    @ColumnInfo(name = "yourChoice")
    var yourChoice: Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

)