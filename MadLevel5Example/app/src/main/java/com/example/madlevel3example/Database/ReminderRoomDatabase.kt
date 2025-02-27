package com.example.madlevel3example.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.madlevel3example.Model.Reminder
import com.example.madlevel3example.dao.ReminderDao

@Database(entities = [Reminder::class], version = 1, exportSchema = false)
abstract class ReminderRoomDatabase : RoomDatabase() {

    abstract fun reminderDao(): ReminderDao

    companion object {
        private const val DATABASE_NAME = "REMINDER_DATABASE"

        @Volatile
        private var reminderRoomDatabaseInstance: ReminderRoomDatabase? = null

        fun getDatabase(context: Context): ReminderRoomDatabase? {
            if (reminderRoomDatabaseInstance == null) {
                synchronized(ReminderRoomDatabase::class.java) {
                    if (reminderRoomDatabaseInstance == null) {
                        reminderRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            ReminderRoomDatabase::class.java,
                            DATABASE_NAME
                        )
                            .build()
                    }
                }
            }
            return reminderRoomDatabaseInstance
        }
    }

}