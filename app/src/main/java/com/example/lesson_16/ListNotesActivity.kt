package com.example.lesson_16

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_16.databinding.ActivityListNotesBinding
import com.example.lesson_16.databinding.ActivityWelcomeBinding

class ListNotesActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityListNotesBinding>(CreateMethod.INFLATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addNoteView.setOnClickListener {
            val intent = Intent(
                this,
                NoteActivity::class.java
            ) // Переход на другую активность после успешного логина
            startActivity(intent)
        }
        binding.logoutTextView.setOnClickListener {
            val intent = Intent(
                this,
                LoginActivity::class.java
            ) // Переход на другую активность после успешного логина
            startActivity(intent)
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val noteSingleton = NoteSingleton.getInstance()
        val notes = noteSingleton.getNotes()

        binding.recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewNotes.adapter = NotesAdapter(notes)
    }

    override fun onResume() {
        super.onResume()
        // Обновляем данные в RecyclerView при возврате к активности
        setupRecyclerView()
    }
}
