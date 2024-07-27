package com.example.lesson_16

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_16.databinding.ActivityNoteBinding
import com.example.lesson_16.databinding.ActivityWelcomeBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NoteActivity: AppCompatActivity() {

    private val binding by viewBinding<ActivityNoteBinding>(CreateMethod.INFLATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            addNote()
        }



    }
    private fun addNote() {
        val title = binding.titleEditText.text.toString()
        val message = binding.messageEditText.text.toString()
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(message)) {
            Toast.makeText(this, "Both fields are required", Toast.LENGTH_SHORT).show()
            return
        }
        val day = binding.datePicker.dayOfMonth
        val month = binding.datePicker.month
        val year = binding.datePicker.year

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = dateFormat.format(calendar.time)

        val note = Note(
            title = title,
            message = message,
            date = date
        )
        NoteSingleton.addNote(note)

        Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show()
        finish()
    }
}