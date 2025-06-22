package com.rombsquare.mentalmath.mainmenu

import androidx.lifecycle.ViewModel
import com.rombsquare.mentalmath.Diff
import com.rombsquare.mentalmath.Operation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainMenuVM : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState

    private var chosenOperation: Operation? = null

    fun onOperationSelected(operation: Operation) {
        chosenOperation = operation
        _uiState.value = UiState.ShowDiffDialog
    }

    fun onDiffSelected(diff: Diff, onNavigate: (String) -> Unit) {
        chosenOperation?.let {
            val route = "game/${diff.string.lowercase()}/${it.string}"
            onNavigate(route)
        }
        _uiState.value = UiState.Idle
    }

    fun onDialogDismissed() {
        _uiState.value = UiState.Idle
    }
}