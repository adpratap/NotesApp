package com.example.notesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noreplypratap.domain.model.DomainNotes
import com.noreplypratap.domain.usecases.CreateNotesUseCase
import com.noreplypratap.domain.usecases.DeleteNotesUseCase
import com.noreplypratap.domain.usecases.ReadNotesUseCase
import com.noreplypratap.domain.usecases.UpdateNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val createNotesUseCase: CreateNotesUseCase,
    private val readNotesUseCase: ReadNotesUseCase,
    private val updateNotesUseCase: UpdateNotesUseCase,
    private val deleteNotesUseCase: DeleteNotesUseCase,
) : ViewModel(){

    private val _notes = MutableLiveData<List<DomainNotes>>()
    val notes: LiveData<List<DomainNotes>> get() = _notes

    init {
        getNotes()
    }
    fun getNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            readNotesUseCase().collect {
                _notes.postValue(it?: emptyList())
            }
        }
    }

    fun saveNote(domainNotes: DomainNotes) = viewModelScope.launch(Dispatchers.IO) {
        createNotesUseCase(domainNotes)
    }
    fun updateNote(domainNotes: DomainNotes) = viewModelScope.launch(Dispatchers.IO) {
        updateNotesUseCase(domainNotes)
    }
    fun deleteNote(domainNotes: DomainNotes) = viewModelScope.launch(Dispatchers.IO) {
        deleteNotesUseCase(domainNotes)
    }
    fun deleteAllNotes() = viewModelScope.launch(Dispatchers.IO) {

    }

}
