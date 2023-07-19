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

    //Get All Data
    @Query("SELECT * FROM notestable")
    fun getNote(): Flow<List<NotesSchema>>

    //Get Specific Data
    @Query("SELECT * FROM notestable WHERE notestable.SNo = :sno")
    fun viewNotes(sno: Int): Flow<NotesSchema>

    //Update the Specific Data
    @Query("UPDATE notestable SET Title =:title,Description =:desc  WHERE notestable.SNo = :sno")
    suspend fun updateNotes(sno: Int, title: String, desc: String)

}