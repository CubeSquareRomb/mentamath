package com.rombsquare.mentalmath.mainmenu

sealed class UiState {
    object ShowDiffDialog : UiState()
    object Idle : UiState()
}