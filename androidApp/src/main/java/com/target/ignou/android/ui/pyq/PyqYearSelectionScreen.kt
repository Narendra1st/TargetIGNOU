package com.target.ignou.android.ui.pyq

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PyqYearSelectionScreen(navController: NavController, subject: String?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(subject ?: "Select Year") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        val years = listOf("2023", "2022", "2021", "2020", "2019")
        val context = LocalContext.current
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(years) { year ->
                ListItem(
                    headlineContent = { Text(year) },
                    modifier = Modifier.clickable {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://webservices.ignou.ac.in/pre-question/Question%20Paper%20June%202017/SOCIS/BCA/BCS-011.PDF")
                        )
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}
