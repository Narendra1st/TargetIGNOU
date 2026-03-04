package com.target.ignou.android.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.target.ignou.model.Pyq
import com.target.ignou.repository.PyqRepository

@Composable
fun PyqScreen(subjectCode: String) {

    val repo = remember { PyqRepository() }
    var pyqs by remember { mutableStateOf<List<Pyq>>(emptyList()) }

    LaunchedEffect(subjectCode) {
        pyqs = repo.getPyqs(subjectCode).pyqs
    }

    LazyColumn {
        items(pyqs) { pyq ->
            Text(
                text = "Year ${pyq.year}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
