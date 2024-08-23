package com.example.lesson_16.data

object NoteSingleton {
    private val notes = mutableListOf<Note>()

    fun addNote(note: Note) {
        notes.add(note)
    }

    fun getNotes(): List<Note> {
        return notes
    }

    fun getInstance(): NoteSingleton {
        return this
    }
}
