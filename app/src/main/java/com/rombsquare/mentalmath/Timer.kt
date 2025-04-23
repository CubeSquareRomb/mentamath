package com.rombsquare.mentalmath

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

@Composable
fun Timer(
    totalTimeSec: Int,
    onTimerUpdate: (Int) -> Unit,
    onTimerFinish: () -> Unit
) {
    var timeLeft by remember { mutableStateOf(totalTimeSec) }

    LaunchedEffect(Unit) {
        while (timeLeft > 0) {
            delay(1000L)
            timeLeft--
            onTimerUpdate(timeLeft)
        }
        onTimerFinish()
    }
}