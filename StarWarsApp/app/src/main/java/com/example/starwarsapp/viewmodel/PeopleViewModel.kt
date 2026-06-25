package com.example.starwarsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapp.data.model.Person
import com.example.starwarsapp.data.repository.PeopleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// ── UI State ──────────────────────────────────────────────────────────────────

sealed class PeopleUiState {
    object Loading : PeopleUiState()
    data class Success(val people: List<Person>) : PeopleUiState()
    data class Error(val message: String) : PeopleUiState()
}

// ── ViewModel ─────────────────────────────────────────────────────────────────

class PeopleViewModel(
    private val repository: PeopleRepository = PeopleRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<PeopleUiState>(PeopleUiState.Loading)
    val uiState: StateFlow<PeopleUiState> = _uiState.asStateFlow()

    // Currently selected person for detail screen
    private val _selectedPerson = MutableStateFlow<Person?>(null)
    val selectedPerson: StateFlow<Person?> = _selectedPerson.asStateFlow()

    // Search query state
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Full list kept in memory for filtering
    private var allPeople: List<Person> = emptyList()

    init {
        fetchPeople()
    }

    fun fetchPeople() {
        viewModelScope.launch {
            _uiState.value = PeopleUiState.Loading
            val result = repository.getPeople()
            _uiState.value = result.fold(
                onSuccess = { people ->
                    allPeople = people
                    PeopleUiState.Success(people)
                },
                onFailure = { e ->
                    PeopleUiState.Error(e.message ?: "Error desconocido")
                }
            )
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        if (query.isBlank()) {
            _uiState.value = PeopleUiState.Success(allPeople)
        } else {
            val filtered = allPeople.filter {
                it.name.contains(query, ignoreCase = true)
            }
            _uiState.value = PeopleUiState.Success(filtered)
        }
    }

    fun selectPerson(person: Person) {
        _selectedPerson.value = person
    }

    fun clearSelection() {
        _selectedPerson.value = null
    }
}
