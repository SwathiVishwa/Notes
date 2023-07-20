package com.app.notes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.notes.R
import com.app.notes.databinding.ActivityAddNoteBinding
import com.app.notes.db.NotesDatabase
import com.app.notes.db.NotesSchema
import com.app.notes.utils.showToast
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
            ivBack.setOnClickListener {
                onBackPressed()
            }
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
                        showToast(
                            applicationContext,
                            getString(R.string.notes_added_successfully)
                        )
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
                showToast(
                    this@AddNoteActivity,
                    getString(R.string.enter_the_data)
                )
                false
            }
        }
    }
}