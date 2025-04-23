package com.rombsquare.mentalmath.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun DifficultyDialog(
    onDismiss: () -> Unit,
    onDifficultySelected: (String) -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Difficulty") },
        text = {
            Column {
                val difficulties = listOf("Easy", "Medium", "Hard", "Extreme")
                difficulties.forEach { difficulty ->
                    TextButton(onClick = {
                        onDifficultySelected(difficulty)
                    }) {
                        Text(difficulty)
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}