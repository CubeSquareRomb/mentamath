package com.rombsquare.mentalmath.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rombsquare.mentalmath.widgets.DifficultyDialog
import com.rombsquare.mentalmath.widgets.SquaredButton

@Composable
fun MainMenu(navController: NavHostController) {
    var showDialog by remember { mutableStateOf(false) }
    var chosenSign by remember { mutableStateOf("+") }

    if (showDialog) {
        DifficultyDialog(
            onDismiss = { showDialog = false },
            onDifficultySelected = { difficulty ->
                showDialog = false
                navController.navigate("game/$difficulty/$chosenSign")
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Choose a sign",
                    color = Color.White,
                    fontSize = 30.sp
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row (
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SquaredButton("+", 100, Color.Green, {
                        showDialog = true
                        chosenSign = "add"
                    })
                    SquaredButton("–", 100, Color(0xff00b3ff), {
                        showDialog = true
                        chosenSign = "sub"
                    })
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SquaredButton("×", 100, Color(0xffff3c00), {
                        showDialog = true
                        chosenSign = "mul"
                    })
                    SquaredButton("÷", 100, Color.Yellow, {
                        showDialog = true
                        chosenSign = "div"
                    })
                }
            }
        }

    }
}