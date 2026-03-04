package com.target.ignou.android.ui.pyq

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.ignou.model.AddSubjectRequest
import com.target.ignou.model.Subject
import com.target.ignou.repository.SubjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PyqViewModel : ViewModel() {

    private val repository = SubjectRepository()

    private val _subjects = MutableStateFlow<List<Subject>>(emptyList())
    val subjects: StateFlow<List<Subject>> = _subjects

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadSubjects(course: String, semester: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                _errorMessage.value = null
                val response = repository.getSubjects(course, semester)
                if (semester == "1" || semester == "sem1") {
                    println("Data for sem1: ${response.subjects}")
                }
                _subjects.value = response.subjects
            } catch (e: Exception) {
                e.printStackTrace()
                println("API Exception: ${e.message}")
                _errorMessage.value = "Error: ${e.message ?: "Unknown"}"
                _subjects.value = emptyList()
            }
            _loading.value = false
        }
    }

    fun addSubject(code: String, name: String, course: String, semester: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val semInt = semester.toIntOrNull() ?: 1
                repository.addSubject(AddSubjectRequest(code, name, course, semInt))
                loadSubjects(course, semester)
            } catch (e: Exception) {
                _loading.value = false
            }
        }
    }
}
