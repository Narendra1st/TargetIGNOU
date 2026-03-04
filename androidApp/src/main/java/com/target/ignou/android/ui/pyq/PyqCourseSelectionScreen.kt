package com.target.ignou.android.ui.pyq

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PyqCourseSelectionScreen(
    navController: NavController,
    viewModel: PyqViewModel = viewModel()
) {
    var selectedCourse by remember { mutableStateOf("BCA") }
    var selectedSemester by remember { mutableStateOf("1") }
    var searchQuery by remember { mutableStateOf("") }

    var showAddDialog by remember { mutableStateOf(false) }
    var newSubjectCode by remember { mutableStateOf("") }
    var newSubjectName by remember { mutableStateOf("") }

    val subjects by viewModel.subjects.collectAsState()
    val loading by viewModel.loading.collectAsState()

    LaunchedEffect(selectedCourse, selectedSemester) {
        viewModel.loadSubjects(
            course = selectedCourse,
            semester = selectedSemester
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Select Subject") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Subject")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier.padding(padding).fillMaxSize()
        ) {

            // 🔹 Course Tabs
            TabRow(selectedTabIndex = if (selectedCourse == "BCA") 0 else 1) {
                listOf("BCA", "MCA").forEachIndexed { index, title ->
                    Tab(
                        selected = selectedCourse == title,
                        onClick = {
                            selectedCourse = title
                            selectedSemester = "1"
                        },
                        text = { Text(title) }
                    )
                }
            }

            // 🔹 Semester Selector
            Row(modifier = Modifier.padding(8.dp)) {
                (1..6).forEach { sem ->
                    val semString = sem.toString()
                    FilterChip(
                        selected = selectedSemester == semString,
                        onClick = { selectedSemester = semString },
                        label = { Text("Sem $sem") },
                        modifier = Modifier.padding(end = 6.dp)
                    )
                }
            }

            // 🔹 Search
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text("Search subject") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            )

            // 🔹 Content
            if (loading) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            } else {
                val error by viewModel.errorMessage.collectAsState()
                if (error != null) {
                    Text(
                        text = error ?: "",
                        color = androidx.compose.ui.graphics.Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(
                        subjects.filter {
                            val query = searchQuery.trim()
                            it.code.contains(query, true) ||
                                    it.name.contains(query, true)
                        }
                    ) { subject ->
                        ListItem(
                            headlineContent = {
                                Text("${subject.code} - ${subject.name}")
                            },
                            modifier = Modifier.clickable {
                                navController.navigate(
                                    "pyq_year_selection/${subject.code}"
                                )
                            }
                        )
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("Add Subject to $selectedCourse Sem $selectedSemester") },
            text = {
                Column {
                    OutlinedTextField(
                        value = newSubjectCode,
                        onValueChange = { newSubjectCode = it },
                        label = { Text("Subject Code") }
                    )
                    OutlinedTextField(
                        value = newSubjectName,
                        onValueChange = { newSubjectName = it },
                        label = { Text("Subject Name") },
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    if (newSubjectCode.isNotBlank() && newSubjectName.isNotBlank()) {
                        viewModel.addSubject(
                            code = newSubjectCode,
                            name = newSubjectName,
                            course = selectedCourse,
                            semester = selectedSemester
                        )
                        newSubjectCode = ""
                        newSubjectName = ""
                        showAddDialog = false
                    }
                }) {
                    Text("Add")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
