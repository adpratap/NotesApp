package com.example.notesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.model.UserNotes
import com.example.notesapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val repository: Repository) : ViewModel(){

    fun getNotes() = repository.getNotes()

    fun saveNote(userNote: UserNotes) = viewModelScope.launch(Dispatchers.IO) {
        repository.saveNote(userNote)
    }

    fun updateNote(userNote: UserNotes) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateNote(userNote)
    }

    fun deleteAllNotes() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllNotes()
    }

    fun deleteNote(userNote: UserNotes) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteNote(userNote)
    }

}
