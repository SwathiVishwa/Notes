package com.app.notes.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.notes.databinding.ActivityMainBinding
import com.app.notes.db.NotesDatabase
import com.app.notes.db.NotesSchema
import com.app.notes.utils.Constants
import com.app.notes.utils.placeHolder
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), RecyclerClickListener {
    private lateinit var notesAdapter: NotesAdapter
    private var list: List<NotesSchema> = listOf()

    private val noteDatabase by lazy { NotesDatabase.getDatabase(this).noteDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getAllData()
    }

    private fun ActivityMainBinding.getAllData() {
        this.apply {
            lifecycleScope.launch {
                noteDatabase.getNote().collect { notesList ->
                    placeHolder(
                        notesList as ArrayList<NotesSchema>, rvNotes,
                        ivPlaceholder, noItem, noDataSub
                    )
                    if (notesList.isNotEmpty()) {
                        list = notesList
                        notesAdapter = NotesAdapter(notesList, this@MainActivity)
                        rvNotes.adapter = notesAdapter
                    }
                }
            }

            ivAdd.setOnClickListener {
                val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onItemClick(position: Int, data: NotesSchema) {
        val intent = Intent(this@MainActivity, ViewNoteActivity::class.java)
        intent.putExtra(Constants.columnSno, data.sNo)
        intent.putExtra(Constants.columnDate, data.date)
        intent.putExtra(Constants.columnTitle, data.title)
        intent.putExtra(Constants.columnDescription, data.description)
        startActivity(intent)
    }
}