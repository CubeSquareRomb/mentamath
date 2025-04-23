package com.rombsquare.mentalmath.widgets

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun WrongAnswerDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Oops!") },
            text = { Text("Your answer is incorrect, try again") },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text("Okay")
                }
            }
        )
    }
}