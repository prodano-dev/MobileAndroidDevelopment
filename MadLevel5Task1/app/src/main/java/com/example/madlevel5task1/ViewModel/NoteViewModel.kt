package com.example.madlevel5task1.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel5task1.Model.Note
import com.example.madlevel5task1.Repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private var ioScope = CoroutineScope(Dispatchers.IO)
    private var noteRepository = NoteRepository(application.applicationContext)

    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()
    val notes: LiveData<Note> = noteRepository.getNotepad()

    fun insertNote(note: Note) {
        ioScope.launch {
            noteRepository.insertNote(note)
        }
    }

    fun updateNote(title: String, text: String) {

        val newNote = Note(title, Date(), text, notes.value?.id)
        if (isNoteValid(newNote)) {
            ioScope.launch {
                noteRepository.updateNote(newNote)
            }
            success.value = true
        }




    }
    private fun isNoteValid(note: Note): Boolean {
        return when {
            note.noteTitle.isBlank() -> {
                error.value = "Title must not be empty"
                false
            }
            else -> true
        }
    }



    fun deletNote(note: Note) {
        ioScope.launch {
            noteRepository.deleteNote(note)
        }
    }
}