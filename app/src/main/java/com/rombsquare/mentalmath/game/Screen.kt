package com.rombsquare.mentalmath.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.rombsquare.mentalmath.Diff
import com.rombsquare.mentalmath.Operation
import com.rombsquare.mentalmath.game.UiState.ShowWrongAnswerDialog


@Composable
fun GameScreen(navController: NavHostController? = null, op: Operation, diff: Diff) {
    val viewModel: GameVM = viewModel(
        factory = GameVMFactory(op, diff)
    )

    val state by viewModel.uiState.collectAsState()
    val solved by viewModel.solved.collectAsState()
    val task by viewModel.task.collectAsState()
    val remainingTime by viewModel.remainingTime.collectAsState()

    when (state) {
        ShowWrongAnswerDialog -> {
            WrongAnswerDialog(
                onDismiss = {
                    viewModel.idle()
                }
            )
        }

        UiState.FinishGame -> {
            EndGameDialog(
                solvedTasks = solved,
                onHomeClick = {
                    navController?.navigate("home")
                }
            )

        }
        null -> {}
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text="Solved: $solved",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )

            Text(
                text="$remainingTime s",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )

        }

        Text(
            text=task,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
        )

        AnswerInput(onSend = { viewModel.submitAnswer(it) })

    }
}
