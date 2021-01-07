package com.example.madlevel5task1.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel5task1.Dao.NoteDao
import com.example.madlevel5task1.Database.NoteRoomDatabase
import com.example.madlevel5task1.Model.Note

class NoteRepository(context: Context) {

    private var noteDao: NoteDao

    init {
        val noteRoomDatabase = NoteRoomDatabase.getDatabase(context)
        noteDao = noteRoomDatabase!!.noteDao()
    }

    fun getNotepad(): LiveData<Note> {
        return noteDao?.getNotepad()
    }


    suspend fun updateNote(note: Note) {
        return noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        return noteDao.deleteNote(note)
    }

    suspend fun insertNote(note: Note){
        return noteDao.insterNote(note)
    }

}