package com.app.notes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.notes.R
import com.app.notes.databinding.ActivityViewNoteBinding
import com.app.notes.db.NotesDatabase
import com.app.notes.utils.Constants
import com.app.notes.utils.backPressed
import com.app.notes.utils.showToast
import kotlinx.coroutines.launch

class ViewNoteActivity : AppCompatActivity() {
    private val noteDatabase by lazy { NotesDatabase.getDatabase(this).noteDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityViewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getData = intent
        val sNo = getData.getIntExtra(Constants.columnSno, 1)
        binding.apply {
            ivBack.setOnClickListener {
                this@ViewNoteActivity.backPressed()
            }
            this.viewSelectedData(sNo)
            tvUpdate.setOnClickListener {
                this.updateSelectedData(sNo)
            }
            ivDelete.setOnClickListener {
                this@ViewNoteActivity.backPressed()
                this.deleteRow(sNo)
            }
        }
    }

    private fun ActivityViewNoteBinding.deleteRow(sNo: Int) {
        this.apply {
            lifecycleScope.launch {
                noteDatabase.deleteRow(
                    sNo
                )

                showToast(applicationContext, getString(R.string.data_deleted_successfully))
            }
        }
    }

    private fun ActivityViewNoteBinding.updateSelectedData(sNo: Int) {
        this.apply {
            lifecycleScope.launch {
                noteDatabase.updateNotes(
                    sNo,
                    edtTitle.text.toString(),
                    edtDescription.text.toString()
                )

                this@ViewNoteActivity.backPressed()

                showToast(applicationContext, getString(R.string.data_updated_successfully))
            }
        }
    }

    private fun ActivityViewNoteBinding.viewSelectedData(sNo: Int) {
        this.apply {
            lifecycleScope.launch {
                noteDatabase.viewNotes(sNo).collect {
                    edtTitle.setText(it?.title)
                    edtDescription.setText(it?.description)
                }
            }
        }
    }
}