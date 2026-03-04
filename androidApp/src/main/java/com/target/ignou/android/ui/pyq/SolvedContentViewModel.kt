package com.target.ignou.android.ui.pyq

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.ignou.model.SolvedPaper
import com.target.ignou.repository.SolvedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class SolvedContentState {
    object Loading : SolvedContentState()
    data class Success(val paper: SolvedPaper) : SolvedContentState()
    data class Error(val message: String) : SolvedContentState()
}

class SolvedContentViewModel : ViewModel() {
    private val repository = SolvedRepository()

    private val _state = MutableStateFlow<SolvedContentState>(SolvedContentState.Loading)
    val state: StateFlow<SolvedContentState> = _state.asStateFlow()

    fun fetchSolvedPaper(subject: String, year: Int) {
        viewModelScope.launch {
            _state.value = SolvedContentState.Loading
            try {
                val paper = repository.getSolvedPaper(subject, year)
                _state.value = SolvedContentState.Success(paper)
            } catch (e: Exception) {
                _state.value = SolvedContentState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}
