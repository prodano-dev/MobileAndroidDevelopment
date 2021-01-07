package com.example.madlevel5task1.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.madlevel5task1.Model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM noteTable LIMIT 1")
    fun getNotepad(): LiveData<Note>

    @Insert
    fun insterNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Update
    fun updateNote(note: Note)
}