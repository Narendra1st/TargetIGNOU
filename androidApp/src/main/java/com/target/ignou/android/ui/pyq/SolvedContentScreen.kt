package com.target.ignou.android.ui.pyq

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SolvedContentScreen(
    navController: NavController,
    subject: String?,
    year: Int?,
    viewModel: SolvedContentViewModel = viewModel()
) {
    LaunchedEffect(key1 = subject, key2 = year) {
        if (subject != null && year != null) {
            viewModel.fetchSolvedPaper(subject, year)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "${subject ?: "Subject"} - ${year ?: "Year"}") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        val state by viewModel.state.collectAsState()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (val currentState = state) {
                is SolvedContentState.Loading -> {
                    CircularProgressIndicator()
                }
                is SolvedContentState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        item {
                            Text(
                                text = currentState.paper.content,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
                is SolvedContentState.Error -> {
                    Text(
                        text = currentState.message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}
