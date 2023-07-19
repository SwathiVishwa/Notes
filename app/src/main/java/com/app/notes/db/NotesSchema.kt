package com.app.notes.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.notes.utils.Constants.coloumnDate
import com.app.notes.utils.Constants.coloumnSno
import com.app.notes.utils.Constants.coloumnTitle
import com.app.notes.utils.Constants.tableName
import java.util.Date

@Entity(tableName = tableName)
data class NotesSchema(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = coloumnSno)
    val sNo: Int? = null,
    @ColumnInfo(name = coloumnDate)
    val date: Date,
    @ColumnInfo(name = coloumnTitle)
    val title: String
)