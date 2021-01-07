package com.example.madlevel5task1.Database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.madlevel5task1.Converters
import com.example.madlevel5task1.Dao.NoteDao
import com.example.madlevel5task1.Model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        private const val DATABASE_NAME = "NOTE_DATABASE"

        @Volatile
        private var noteRoomDatabaseInstance: NoteRoomDatabase? = null

        fun getDatabase(context: Context): NoteRoomDatabase? {
            if (noteRoomDatabaseInstance == null) {
                synchronized(NoteRoomDatabase::class.java) {
                    if (noteRoomDatabaseInstance == null) {
                        noteRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            NoteRoomDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .addCallback(object : RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    noteRoomDatabaseInstance?.let { database ->
                                        CoroutineScope(Dispatchers.IO).launch {
                                            database.noteDao().insterNote(Note("Title", Date(), ""))
                                        }
                                    }
                                }
                            })

                            .build()
                    }
                }
            }
            return noteRoomDatabaseInstance
        }
    }
}
