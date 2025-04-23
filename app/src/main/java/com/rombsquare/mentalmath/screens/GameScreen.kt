package com.rombsquare.mentalmath.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rombsquare.mentalmath.Engine
import com.rombsquare.mentalmath.widgets.AnswerInput
import com.rombsquare.mentalmath.Timer
import com.rombsquare.mentalmath.widgets.TimeUpDialog
import com.rombsquare.mentalmath.widgets.WrongAnswerDialog

@Preview(showBackground = true)
@Composable
fun GameScreen(navController: NavHostController? = null, difficulty: String = "Medium", sign: String = "+") {
    val engine by remember { mutableStateOf(Engine(sign, difficulty)) }
    var showDialog by remember { mutableStateOf(false) }
    var secondsLeft by remember { mutableStateOf(100) }

    WrongAnswerDialog(
        showDialog = showDialog,
        onDismiss = { showDialog = false }
    )

    TimeUpDialog(
        showDialog = secondsLeft == 0,
        solvedTasks = engine.solved,
        onHomeClick = {
            navController?.navigate("home")
        }
    )

    Timer(
        totalTimeSec = 100,
        onTimerUpdate = {
            secondsLeft = it
        },
        onTimerFinish = {}
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text="Solved: ${engine.solved}",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )

            Text(
                text="$secondsLeft s",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )

        }

        Text(
            text=engine.task,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
        )

        AnswerInput(onSend = {
            if (!engine.check(it.toInt())) {
                showDialog = true
            }
        })

    }
}
