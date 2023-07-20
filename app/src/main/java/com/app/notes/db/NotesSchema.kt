package com.app.notes.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.notes.utils.Constants.columnDate
import com.app.notes.utils.Constants.columnDescription
import com.app.notes.utils.Constants.columnSno
import com.app.notes.utils.Constants.columnTitle
import com.app.notes.utils.Constants.tableName
import java.util.Date


@Entity(tableName = tableName)
data class NotesSchema(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = columnSno)
    val sNo: Int? = null,
    @ColumnInfo(name = columnDate)
    val date: Date,
    @ColumnInfo(name = columnTitle)
    val title: String?,
    @ColumnInfo(name = columnDescription)
    val description: String?
)