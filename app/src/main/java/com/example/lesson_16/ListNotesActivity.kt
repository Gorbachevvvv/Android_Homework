package com.example.lesson_16

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_16.databinding.ActivityListNotesBinding

class ListNotesActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityListNotesBinding>(CreateMethod.INFLATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addNoteView.setOnClickListener {
            val intent = Intent(
                this,
                NoteActivity::class.java
            )
            startActivity(intent)
        }
        binding.logoutTextView.setOnClickListener {
            val intent = Intent(
                this,
                LoginActivity::class.java
            )
            startActivity(intent)
        }
        setupListView()
    }

    private fun setupListView() {
        val noteSingleton = NoteSingleton.getInstance()
        val notes = noteSingleton.getNotes()

        val adapter = NoteAdapter(this, notes)
        binding.listViewNotes.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        setupListView()
    }
}
