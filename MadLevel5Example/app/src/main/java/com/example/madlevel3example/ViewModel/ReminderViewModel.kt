package com.example.madlevel3example.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.madlevel3example.Model.Reminder
import com.example.madlevel3example.Repository.ReminderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReminderViewModel (application: Application) : AndroidViewModel(application) {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val reminderRepository = ReminderRepository(application.applicationContext)

    public val reminders: LiveData<List<Reminder>> = reminderRepository.getAllReminders()

    public fun insertReminder(reminder: Reminder) {
        ioScope.launch {
            reminderRepository.insertReminder(reminder)
        }
    }

    fun deleteReminder(reminder: Reminder) {
        ioScope.launch {
            reminderRepository.deleteReminder(reminder)
        }
    }
}