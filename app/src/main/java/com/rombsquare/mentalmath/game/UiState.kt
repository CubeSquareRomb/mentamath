package com.rombsquare.mentalmath.game

sealed class UiState {
    object FinishGame: UiState()
    object ShowWrongAnswerDialog: UiState()
}