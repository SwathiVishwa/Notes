package com.app.notes.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.notes.R
import com.app.notes.databinding.ActivityAddNoteBinding
import com.app.notes.db.NotesDatabase
import com.app.notes.db.NotesSchema
import kotlinx.coroutines.launch
import java.util.Date

class AddNoteActivity : AppCompatActivity() {
    private val noteDatabase by lazy { NotesDatabase.getDatabase(this).noteDao() }
    private val noteDateAdded = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            tvDone.setOnClickListener {
                if (validate(binding)) {
                    val newNote =
                        NotesSchema(
                            date = noteDateAdded,
                            title = binding.edtTitle.text.toString(),
                            description = binding.edtDescription.text.toString()
                        )
                    lifecycleScope.launch {
                        noteDatabase.addNote(newNote)
                        onBackPressed()
                        Toast.makeText(
                            this@AddNoteActivity,
                            getString(R.string.notes_added_successfully),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun validate(binding: ActivityAddNoteBinding): Boolean {
        binding.apply {
            return if ((edtTitle.text.toString().isNotEmpty()) && (edtDescription.text.toString()
                    .isNotEmpty())
            ) {
                true
            } else {
                Toast.makeText(
                    this@AddNoteActivity,
                    getString(R.string.enter_the_data), Toast.LENGTH_LONG
                ).show()
                false
            }
        }
    }
}