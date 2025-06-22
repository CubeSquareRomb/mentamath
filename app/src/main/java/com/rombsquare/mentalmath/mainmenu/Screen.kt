package com.rombsquare.mentalmath.mainmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun MainMenu(navController: NavHostController) {
    val viewModel: MainMenuVM = viewModel()
    val uiState by viewModel.uiState.collectAsState()


    if (uiState is UiState.ShowDiffDialog) {
        DifficultyDialog(
            onDismiss = { viewModel.onDialogDismissed() },
            onDifficultySelected = { difficulty ->
                viewModel.onDiffSelected(difficulty) { route ->
                    navController.navigate(route)
                }
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

            MenuButtons(onClick = { viewModel.onOperationSelected(it) })
        }
    }
}