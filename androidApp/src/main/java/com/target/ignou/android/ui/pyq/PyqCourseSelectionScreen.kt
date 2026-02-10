package com.target.ignou.android.ui.pyq

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PyqCourseSelectionScreen(navController: NavController) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("BCA", "MCA")
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Course") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search Subjects") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(text = title) }
                    )
                }
            }
            when (selectedTabIndex) {
                0 -> BcaSubjectList(navController, searchQuery)
                1 -> McaSubjectList(navController, searchQuery)
            }
        }
    }
}

@Composable
fun BcaSubjectList(navController: NavController, searchQuery: String) {
    val subjects = listOf(
        "BCS-011: Computer Basics and PC Software",
        "BCS-012: Mathematics",
        "BCSL-013: Computer Basics and PC Software Lab",
        "FEG-02: Foundation course in English-2",
        "ECO-01: Business Organization"
    ).filter { it.contains(searchQuery, ignoreCase = true) }
    LazyColumn {
        items(subjects) { subject ->
            ListItem(
                headlineContent = { Text(subject) },
                modifier = Modifier.clickable { navController.navigate("pyq_year_selection/$subject") }
            )
        }
    }
}

@Composable
fun McaSubjectList(navController: NavController, searchQuery: String) {
    val subjects = listOf(
        "MCS-011: Problem Solving and Programming",
        "MCS-012: Computer Organization and Assembly Language Programming",
        "MCS-013: Discrete Mathematics",
        "MCS-014: Systems Analysis and Design",
        "MCS-015: Communication Skills"
    ).filter { it.contains(searchQuery, ignoreCase = true) }
    LazyColumn {
        items(subjects) { subject ->
            ListItem(
                headlineContent = { Text(subject) },
                modifier = Modifier.clickable { navController.navigate("pyq_year_selection/$subject") }
            )
        }
    }
}
