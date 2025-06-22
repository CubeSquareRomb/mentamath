package com.rombsquare.mentalmath.mainmenu

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.rombsquare.mentalmath.Diff

@Composable
fun DifficultyDialog(
    onDismiss: () -> Unit,
    onDifficultySelected: (Diff) -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Difficulty") },
        text = {
            Column {
                Diff.entries.forEach {
                    TextButton(onClick = {
                        onDifficultySelected(it)
                    }) {
                        Text(it.string)
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