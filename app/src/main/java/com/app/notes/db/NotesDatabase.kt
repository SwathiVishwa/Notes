package com.app.notes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.notes.utils.Constants.notesDatabase
import com.app.notes.utils.Utils

@Database(entities = [NotesSchema::class], version = 1, exportSchema = true)
@TypeConverters(Utils::class)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NotesDao

    companion object {
        @Volatile
        var INSTANCE: NotesDatabase? = null
        fun getDatabase(context: Context): NotesDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): NotesDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                NotesDatabase::class.java,
                notesDatabase
            ).build()
        }
    }
}