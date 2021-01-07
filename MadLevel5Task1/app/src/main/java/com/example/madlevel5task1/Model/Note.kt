package com.example.madlevel5task1.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "noteTable")
data class Note(

    @ColumnInfo(name = "title")
    var noteTitle: String,

    @ColumnInfo(name = "lastUpdated")
    var lastUpdated: Date,

    @ColumnInfo(name = "note")
    var note: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

)