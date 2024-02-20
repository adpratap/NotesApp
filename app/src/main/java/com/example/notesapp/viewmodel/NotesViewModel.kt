package com.example.notesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noreplypratap.domain.model.DomainNotes
import com.noreplypratap.domain.model.NotesUseCases
import com.noreplypratap.domain.usecases.CreateNotesUseCase
import com.noreplypratap.domain.usecases.DeleteDatabaseUseCase
import com.noreplypratap.domain.usecases.DeleteNotesUseCase
import com.noreplypratap.domain.usecases.ReadNotesUseCase
import com.noreplypratap.domain.usecases.UpdateNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesUseCases: NotesUseCases
) : ViewModel(){

    private val _notes = MutableLiveData<List<DomainNotes>>()
    val notes: LiveData<List<DomainNotes>> get() = _notes

    init {
        getNotes()
    }
    fun getNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            notesUseCases.readNotesUseCase().collect {
                _notes.postValue(it)
            }
        }
    }

    fun saveNote(domainNotes: DomainNotes) = viewModelScope.launch(Dispatchers.IO) {
        notesUseCases.createNotesUseCase(domainNotes)
    }
    fun updateNote(domainNotes: DomainNotes) = viewModelScope.launch(Dispatchers.IO) {
        notesUseCases.updateNotesUseCase(domainNotes)
    }
    fun deleteNote(domainNotes: DomainNotes) = viewModelScope.launch(Dispatchers.IO) {
        notesUseCases.deleteNotesUseCase(domainNotes)
    }
    fun deleteAllNotes() = viewModelScope.launch(Dispatchers.IO) {
        notesUseCases.deleteDatabaseUseCase()
    }

}
