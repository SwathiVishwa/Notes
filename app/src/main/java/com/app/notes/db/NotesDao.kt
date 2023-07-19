package com.app.notes.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    // onConflict is to ignore any new note that is exactly the same as one already in the list.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(notes: NotesSchema)

    @Query("SELECT * FROM notestable")
    fun getNote(): Flow<List<NotesSchema>>
}