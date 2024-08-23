package com.example.lesson_16

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson_16.data.Note
import com.example.lesson_16.data.NoteSingleton

class MainViewModel : ViewModel() {

    private val _currentPage = MutableLiveData<Int>().apply { value = 0 }
    val currentPage: LiveData<Int> = _currentPage

    private val _notes = MutableLiveData<List<Note>>().apply { value = NoteSingleton.getNotes() }
    val notes: LiveData<List<Note>> = _notes

    private val _isUserLoggedIn = MutableLiveData<Boolean>().apply { value = false }
    val isUserLoggedIn: LiveData<Boolean> = _isUserLoggedIn

    fun setCurrentPage(page: Int) {
        _currentPage.value = page
    }

    fun addNote(note: Note) {
        NoteSingleton.addNote(note)
        _notes.value = NoteSingleton.getNotes()
    }

    fun loginUser(username: String, password: String): Boolean {
        // логика для проверки учетных данных
        if (username.isNotEmpty() && password.isNotEmpty()) {
            _isUserLoggedIn.value = true
            return true
        }
        return false
    }

    fun registerUser(username: String, password: String): Boolean {
        // Логика регистрации пользователя
        if (username.isNotEmpty() && password.isNotEmpty()) {
            _isUserLoggedIn.value = true
            return true
        }
        return false
    }
}
